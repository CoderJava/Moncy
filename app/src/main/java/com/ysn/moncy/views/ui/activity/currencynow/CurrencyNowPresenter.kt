package com.ysn.moncy.views.ui.activity.currencynow

import com.ysn.moncy.api.currency.CurrencyEndpoints
import com.ysn.moncy.api.currencyconverter.CurrencyConverterEndpoints
import com.ysn.moncy.model.currencynow.Rate
import com.ysn.moncy.model.currencynow.Result
import com.ysn.moncy.views.base.mvp.BasePresenter
import com.ysn.moncy.views.ui.activity.currencynow.adapter.AdapterCurrencyNow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class CurrencyNowPresenter(private val currencyEndpoints: CurrencyEndpoints,
                           private val currencyConverterEndpoints: CurrencyConverterEndpoints) : BasePresenter<CurrencyNowView>() {

    private lateinit var adapterCurrencyNow: AdapterCurrencyNow
    private lateinit var rates: MutableList<Rate>
    private lateinit var reverseRates: MutableList<Rate>

    fun onLoadData() {
        rates = mutableListOf()
        reverseRates = mutableListOf()
        adapterCurrencyNow = AdapterCurrencyNow(rates = rates, reverseRates = reverseRates, listenerAdapterCurrencyNow = object : AdapterCurrencyNow.ListenerAdapterCurrencyNow {
            override fun onClick(adapterPosition: Int, name: String) {
                adapterCurrencyNow.updateItem(key = "loading", result = Result(), adapterPosition = adapterPosition)
                onLoadReverseCurrency(adapterPosition = adapterPosition, name = name)
            }
        })
        var source: String
        var datetimeFormatting: String
        currencyEndpoints.getLiveCurrency()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            it.quotes.forEach { key, value ->
                                rates.add(Rate(name = key, value = value.toString()))
                                reverseRates.add(Rate(name = "", value = "0.0"))
                            }
                            datetimeFormatting = SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.US).format(Date(it.timestamp * 1000))
                            source = it.source
                            adapterCurrencyNow.refresh(rates = rates, reverseRates = reverseRates)
                            view?.loadData(adapterCurrencyNow = adapterCurrencyNow, datetimeFormatting = datetimeFormatting, source = source)
                        },
                        {
                            it.printStackTrace()
                            view?.loadDataFailed(message = it.message)
                        },
                        {
                            /* nothing to do in here */
                        }
                )
    }

    private fun onLoadReverseCurrency(adapterPosition: Int, name: String) {
        val query = "${name.substring(3, 6)}_${name.substring(0, 3)}"
        currencyConverterEndpoints.getConverter(q = query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            it.results.forEach { key, result ->
                                adapterCurrencyNow.updateItem(key = key, result = result, adapterPosition = adapterPosition)
                            }
                        },
                        {
                            it.printStackTrace()
                        },
                        {
                            /* nothing to do in here */
                        }
                )
    }
}