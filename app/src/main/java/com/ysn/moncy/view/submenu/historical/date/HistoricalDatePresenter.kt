package com.ysn.moncy.view.submenu.historical.date

import android.content.Context
import com.ysn.moncy.BuildConfig
import com.ysn.moncy.model.country.Country
import com.ysn.moncy.model.currency.historical.Historical
import com.ysn.moncy.model.merge.live.MergeHistorical
import com.ysn.moncy.network.ApiCountryService
import com.ysn.moncy.network.ApiCurrencyService
import com.ysn.moncy.network.NetworkClient
import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by root on 20/08/17.
 */
class HistoricalDatePresenter : MvpPresenter<HistoricalDateView> {

    private val TAG = javaClass.simpleName
    private var historicalDateView: HistoricalDateView? = null
    private lateinit var context: Context
    private lateinit var listQuotesLabels: ArrayList<String>
    private lateinit var listQuotesValues: ArrayList<String>
    private lateinit var listMergeHistorical: ArrayList<MergeHistorical>

    override fun onAttach(mvpView: MvpView) {
        historicalDateView = mvpView as HistoricalDateView
    }

    override fun onDetach() {
        historicalDateView = null
    }

    fun onSubmit(context: Context, dateHistory: String) {
        this.context = context

        /** prepate data history currency */
        var historical: Historical? = null
        val apiCurrency = NetworkClient.RetrofitCurrency
                .getRetrofitCurrency()
                ?.create(ApiCurrencyService::class.java)

        /** prepate data country */
        val apiCountry = NetworkClient.RetrofitCountry
                .getRetrofitCountry()
                ?.create(ApiCountryService::class.java)

        /** load data history currency and data country */
        val observableHistoryCurrency = apiCurrency!!.getHistoricalCurrencies(
                BuildConfig.API_KEY_CURRENCY,
                dateHistory
        )
        val observableCountry = apiCountry!!.getDataCountry("name;flag;currencies")

        /** merge */
        Observable
                .combineLatest(
                        observableHistoryCurrency,
                        observableCountry,
                        BiFunction<Historical, List<Country>, List<MergeHistorical>> { p0, p1 ->
                            historical = p0
                            listQuotesValues = ArrayList<String>()
                            listQuotesLabels = ArrayList<String>()
                            val mapQuotes = historical?.quotes
                            val keys = mapQuotes?.keys?.iterator()
                            val source = historical?.source
                            while (keys?.hasNext()!!) {
                                val key = keys.next().let {
                                    it.substring(source?.length as Int, it.length)
                                }
                                listQuotesLabels.add(key)
                                listQuotesValues.add(mapQuotes[source + key].toString())
                            }
                            val listCountryTemp = p1 as ArrayList<Country>

                            listMergeHistorical = ArrayList<MergeHistorical>()
                            for (a in 0 until listQuotesLabels.size) {
                                val codeQuote = listQuotesLabels[a]
                                val valueQuote = listQuotesValues[a]
                                var countryName: String? = null
                                var flag: String? = null
                                (0 until listCountryTemp.size).forEach { b ->
                                    for (country in listCountryTemp[b].currencies) {
                                        val code = country.code
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
                                    listMergeHistorical.add(
                                            MergeHistorical(
                                                    label = codeQuote,
                                                    value = valueQuote,
                                                    countryName = countryName!!,
                                                    flag = flag!!
                                            )
                                    )
                                }
                            }

                            listMergeHistorical
                        }
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            data: List<MergeHistorical> ->
                            listMergeHistorical = data as ArrayList<MergeHistorical>
                        },
                        {
                            t: Throwable ->
                            t.printStackTrace()
                            historicalDateView?.submitFailed()
                        },
                        {
                            historicalDateView?.submit(listMergeHistorical)
                        }
                )
    }

}