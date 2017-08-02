package com.ysn.moncy.view.submenu.live

import android.content.Context
import com.ysn.moncy.BuildConfig
import com.ysn.moncy.model.country.Country
import com.ysn.moncy.model.currency.live.CurrencyNow
import com.ysn.moncy.model.merge.live.MergeLive
import com.ysn.moncy.network.ApiCountryService
import com.ysn.moncy.network.ApiCurrencyService
import com.ysn.moncy.network.NetworkClient
import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.main.adapter.AdapterCurrencyNow
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by root on 21/07/17.
 */
class CurrencyNowPresenter : MvpPresenter<CurrencyNowView> {

    private val TAG = javaClass.simpleName
    private lateinit var context: Context
    private var currencyNowView: CurrencyNowView? = null
    private var adapterCurrencyNow: AdapterCurrencyNow? = null
    private lateinit var listQuotesLabels: ArrayList<String>
    private lateinit var listQuotesValues: ArrayList<String>
    private lateinit var listMergeLive: ArrayList<MergeLive>

    override fun onAttach(mvpView: MvpView) {
        currencyNowView = mvpView as CurrencyNowView
    }

    override fun onDetach() {
        currencyNowView = null
    }


    fun onLoadData(context: Context, isRefresh: Boolean = false) {
        this.context = context

        /** prepare data currency */
        var currencyNow: CurrencyNow? = null
        val apiCurrency = NetworkClient.RetrofitCurrency
                .getRetrofitCurrency()?.create(ApiCurrencyService::class.java)

        /** prepare data country */
        val apiCountry = NetworkClient.RetrofitCountry
                .getRetrofitCountry()
                ?.create(ApiCountryService::class.java)

        /** load data currency and data country */
        val observableCurrencyNow = apiCurrency!!.getLive(BuildConfig.API_KEY_CURRENCY)
        val observableCountry = apiCountry!!.getDataCountry("name;flag;currencies")

        /** merge */
        Observable
                .combineLatest(
                        observableCurrencyNow,
                        observableCountry,
                        BiFunction<CurrencyNow, List<Country>, List<MergeLive>> { p0, p1 ->
                            currencyNow = p0
                            listQuotesValues = ArrayList<String>()
                            listQuotesLabels = ArrayList<String>()
                            val mapQuotes = currencyNow?.quotes
                            val keys = mapQuotes?.keys?.iterator()
                            val source = currencyNow?.source
                            while (keys?.hasNext()!!) {
                                val key = keys.next().let {
                                    it.substring(source?.length as Int, it.length)
                                }
                                listQuotesLabels.add(key)
                                listQuotesValues.add(mapQuotes[source + key].toString())
                            }
                            val listCountryTemp = p1 as ArrayList<Country>

                            listMergeLive = ArrayList<MergeLive>()
                            for (a in 0 until listQuotesLabels.size) {
                                val codeQuote = listQuotesLabels[a]
                                val valueQuote = listQuotesValues[a]
                                var countryName: String? = null
                                var flag: String? = null
                                for (b in 0 until listCountryTemp.size) {
                                    for (currency in listCountryTemp[b].currencies) {
                                        val code = currency.code
                                        if (code == codeQuote) {
                                            if (countryName.isNullOrEmpty()) {
                                                countryName = listCountryTemp[b].name
                                                flag = listCountryTemp[b].flag
                                            } else {
                                                countryName += "\n" + listCountryTemp[b].name
                                                flag = "-"
                                            }
                                            break
                                        }
                                    }
                                }

                                if (!countryName.isNullOrEmpty()) {
                                    listMergeLive.add(
                                            MergeLive(
                                                    label = codeQuote,
                                                    value = valueQuote,
                                                    countryName = countryName!!,
                                                    flag = flag!!
                                            )
                                    )
                                }
                            }
                            listMergeLive
                        }
                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            data: List<MergeLive> ->
                            listMergeLive = data as ArrayList<MergeLive>
                        },
                        {
                            t: Throwable ->
                            t.printStackTrace()
                            currencyNowView?.loadDataFailed(isRefresh)
                        },
                        {
                            adapterCurrencyNow = AdapterCurrencyNow(
                                    context = context,
                                    listMergeLive = listMergeLive
                            )
                            currencyNowView?.loadData(adapterCurrencyNow!!, currencyNow, isRefresh)
                        }
                )

    }

}
