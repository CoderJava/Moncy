package com.ysn.moncy.view.submenu.convert

import android.content.Context
import android.os.Build
import android.util.Log
import com.ysn.moncy.model.country.Country
import com.ysn.moncy.model.currency.convert.ConvertCurrency
import com.ysn.moncy.model.currency.convert.CurrencyFixer
import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import com.ysn.moncy.network.ApiCountryService
import com.ysn.moncy.network.ApiFixerService
import com.ysn.moncy.network.NetworkClient
import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by root on 26/08/17.
 */
class ConvertCurrencyPresenter : MvpPresenter<ConvertCurrencyView> {

    private val TAG = javaClass.simpleName
    private var convertCurrencyView: ConvertCurrencyView? = null
    private lateinit var context: Context
    private lateinit var listMergeConvertCurrency: List<MergeConvertCurrency>

    override fun onAttach(mvpView: MvpView) {
        convertCurrencyView = mvpView as ConvertCurrencyView
    }

    override fun onDetach() {
        convertCurrencyView = null
    }

    fun onLoadData(context: Context) {
        this.context = context

        /** prepare data convert currency */
        val apiCurrency = NetworkClient.RetrofitFixer
                .getRetrofitCurrency()
                ?.create(ApiFixerService::class.java)

        /** prepare data country */
        val apiCountry = NetworkClient.RetrofitCountry
                .getRetrofitCountry()
                ?.create(ApiCountryService::class.java)

        /** load data convert currency and data country */
        val observableAvailableCurrency = apiCurrency!!
                .getListCurrency()
        val observableCountry = apiCountry!!
                .getDataCountry("name;region;latlng;currencies;flag")

        /** merge */
        Observable.
                combineLatest(
                        observableAvailableCurrency,
                        observableCountry,
                        BiFunction<CurrencyFixer, List<Country>, List<MergeConvertCurrency>> { currencyFixer, listCountry ->
                            val listMergeConvertCurrency = ArrayList<MergeConvertCurrency>()

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                currencyFixer.rates.forEach { key, _ ->
                                    for (country in listCountry) {
                                        var isBreak = false
                                        for ((code) in country.currencies) {
                                            if (code == key) {
                                                val mergeConvertCurrency = MergeConvertCurrency(
                                                        currencyCode = code,
                                                        flag = country.flag
                                                )
                                                listMergeConvertCurrency.add(mergeConvertCurrency)
                                                isBreak = true
                                                break
                                            }
                                        }
                                        if (isBreak) {
                                            break
                                        }
                                    }
                                }
                            } else {
                                for ((key) in currencyFixer.rates) {
                                    for (country in listCountry) {
                                        var isBreak = false
                                        for ((code) in country.currencies) {
                                            if (code == key) {
                                                val mergeConvertCurrency = MergeConvertCurrency(
                                                        currencyCode = code,
                                                        flag = country.flag
                                                )
                                                listMergeConvertCurrency.add(mergeConvertCurrency)
                                                isBreak = true
                                                break
                                            }
                                        }
                                        if (isBreak) {
                                            break
                                        }
                                    }
                                }
                            }

                            listMergeConvertCurrency
                        }
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data: List<MergeConvertCurrency> ->
                            listMergeConvertCurrency = data
                        },
                        { t: Throwable ->
                            t.printStackTrace()
                            convertCurrencyView?.loadDataFailed()
                        },
                        {
                            convertCurrencyView?.loadData(listMergeConvertCurrency)
                        }
                )

    }

    fun onLoadDataConverterCurrency(sourceCode: String, toCode: String) {
        /** prepare data converter currency */
        val apiCurrency = NetworkClient
                .RetrofitFixer
                .getRetrofitCurrency()
                ?.create(ApiFixerService::class.java)

        /** load data converter currency */
        val observableConverterCurrency = apiCurrency?.getSpecifiedCurrency(
                base = sourceCode,
                symbols = toCode
        )

        observableConverterCurrency?.map { convertCurrency: ConvertCurrency ->
            convertCurrency.rates[toCode] ?: -1.0
        }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        { valueConverterCurrency: Double ->
                            Log.d(TAG, "valueConvertCurrency: $valueConverterCurrency")
                            convertCurrencyView?.loadDataConverterCurrency(valueConverterCurrency)
                        },
                        { t: Throwable ->
                            t.printStackTrace()
                            convertCurrencyView?.loadDataConverterCurrencyFailed()
                        }
                )

    }

    fun doConvertCurrency(sourceAmount: Double, valueConverterCurrency: Double): Double =
            sourceAmount * valueConverterCurrency

}