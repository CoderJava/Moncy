package com.ysn.moncy.view.submenu.available

import android.content.Context
import android.util.Log
import com.ysn.moncy.BuildConfig
import com.ysn.moncy.model.country.Country
import com.ysn.moncy.model.currency.available.AvailableCurrency
import com.ysn.moncy.model.merge.available.MergeAvailable
import com.ysn.moncy.network.ApiCountryService
import com.ysn.moncy.network.ApiCurrencyService
import com.ysn.moncy.network.NetworkClient
import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.available.adapter.AdapterAvailableCurrency
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by root on 30/07/17.
 */
class AvailableCurrencyPresenter : MvpPresenter<AvailableCurrencyView> {

    private val TAG = javaClass.simpleName
    private var availableCurrencyView: AvailableCurrencyView? = null
    private lateinit var context: Context
    private lateinit var listMergeAvailable: ArrayList<MergeAvailable>
    private var adapterAvailableCurrency: AdapterAvailableCurrency? = null

    override fun onAttach(mvpView: MvpView) {
        availableCurrencyView = mvpView as AvailableCurrencyView
    }

    override fun onDetach() {
        availableCurrencyView = null
    }


    fun onLoadData(context: Context, isRefresh: Boolean = false) {
        this.context = context

        /** prepare data available currency */
        val apiCurrency = NetworkClient.RetrofitCurrency
                .getRetrofitCurrency()
                ?.create(ApiCurrencyService::class.java)

        /** prepare data country */
        val apiCountry = NetworkClient.RetrofitCountry
                .getRetrofitCountry()
                ?.create(ApiCountryService::class.java)

        /** load data available currency and data country */
        val observableAvailableCurrency = apiCurrency!!
                .getAvailableCurrency(BuildConfig.API_KEY_CURRENCY)
        Log.d(TAG, "success load data available currency")
        val observableCountry = apiCountry!!
                .getDataCountry("name;region;latlng;currencies;flag")
        Log.d(TAG, "success load data country")

        /** merge */
        Observable
                .combineLatest(
                        observableAvailableCurrency,
                        observableCountry,
                        BiFunction<AvailableCurrency, List<Country>, List<MergeAvailable>> { p0, p1 ->
                            val availableCurrency = p0
                            val listCountries = p1
                            val listMergeAvailable = ArrayList<MergeAvailable>()

                            val iteratorKey = availableCurrency.currencies.keys.iterator()
                            while (iteratorKey.hasNext()) {
                                var mergeAvailable: MergeAvailable
                                val code = iteratorKey.next()
                                val name = availableCurrency.currencies[code]
                                listCountries.forEach { (regionItem, _, flagItem, _, currencies) ->
                                    currencies.forEach {
                                        if (code == it.code) {
                                            mergeAvailable = MergeAvailable(
                                                    name = name!!,
                                                    codeCurrency = code,
                                                    region = regionItem!!,
                                                    flag = flagItem
                                            )
                                            listMergeAvailable.add(mergeAvailable)
                                        }
                                    }
                                }
                            }
                            listMergeAvailable
                        }
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            data: List<MergeAvailable> ->
                            listMergeAvailable = data as ArrayList<MergeAvailable>
                        },
                        {
                            t: Throwable ->
                            t.printStackTrace()
                            availableCurrencyView?.loadDataFailed(isRefresh)
                        },
                        {
                            Log.d(TAG, "listMergeAvailable.size: " + listMergeAvailable.size)
                            Log.d(TAG, "listMergeAvailable: " + listMergeAvailable)
                            adapterAvailableCurrency = AdapterAvailableCurrency(
                                    context = context,
                                    listMergeAvailable = listMergeAvailable
                            )
                            availableCurrencyView?.loadData(adapterAvailableCurrency!!, isRefresh)
                        }
                )
    }

}