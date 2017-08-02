package com.ysn.moncy.view.submenu.available

import android.content.Context
import com.ysn.moncy.BuildConfig
import com.ysn.moncy.model.country.Country
import com.ysn.moncy.model.currency.available.AvailableCurrency
import com.ysn.moncy.model.merge.available.MergeAvailable
import com.ysn.moncy.network.ApiCountryService
import com.ysn.moncy.network.ApiCurrencyService
import com.ysn.moncy.network.NetworkClient
import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * Created by root on 30/07/17.
 */
class AvailableCurrencyPresenter : MvpPresenter<AvailableCurrencyView> {

    private val TAG = javaClass.simpleName
    private var availableCurrencyView: AvailableCurrencyView? = null
    private var context: Context? = null

    override fun onAttach(mvpView: MvpView) {
        availableCurrencyView = mvpView as AvailableCurrencyView
    }

    override fun onDetach() {
        availableCurrencyView = null
    }


    fun onLoadData(availableCurrencyActivity: AvailableCurrencyActivity) {
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
        val observableCountry = apiCountry!!
                .getDataCountry("name;region;latlng;currencies;flag")

        /** merge */
        Observable
                .combineLatest(
                        observableAvailableCurrency,
                        observableCountry,
                        BiFunction<AvailableCurrency, List<Country>, List<MergeAvailable>> { p0, p1 ->
                            // todo: do something
                            /*val availableCurrency = p0
                            val listCountries = p1*/
                            ArrayList<MergeAvailable>()
                        }
                )
    }

}