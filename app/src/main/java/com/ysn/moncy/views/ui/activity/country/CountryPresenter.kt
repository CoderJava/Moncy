package com.ysn.moncy.views.ui.activity.country

import com.ysn.moncy.api.country.CountryEndpoints
import com.ysn.moncy.api.countrycurrency.CountryCurrencyEndpoints
import com.ysn.moncy.model.country.Country
import com.ysn.moncy.model.country.CountryData
import com.ysn.moncy.views.base.mvp.BasePresenter
import com.ysn.moncy.views.ui.activity.country.adapter.AdapterCountry
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class CountryPresenter constructor(private val countryEndpoints: CountryEndpoints,
                                   private val countryCurrencyEndpoints: CountryCurrencyEndpoints) : BasePresenter<CountryView>() {

    private lateinit var adapterCountry: AdapterCountry
    private var countries = mutableListOf<Country>()

    fun onLoadData() {
        val context = view?.getViewContext()
        adapterCountry = AdapterCountry(context = context!!, countries = countries)
        val observableCountry = countryEndpoints.getCountries()
        val observableCountryCurrency = countryCurrencyEndpoints.getCurrencyCode()
        Observable
                .zip(observableCountry, observableCountryCurrency, BiFunction<List<CountryData>, Map<String, String>, Boolean> { countryDatas, mapCountryCurrency ->
                    mapCountryCurrency.forEach { _, value ->
                        for (index in countryDatas.indices) {
                            val countryData = countryDatas[index]
                            if (countryData.currencies[0].code == value) {
                                val countryName = countryData.name
                                val symbolCurrency = value
                                val capitalCity = countryData.capital
                                val flagCountry = countryData.flag
                                val country = Country(flag = flagCountry, name = countryName, symbolCurrency = symbolCurrency, capitalCity = capitalCity)
                                countries.add(country)
                                break
                            }
                        }
                    }
                    true
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            adapterCountry.refresh(countries = countries)
                            view?.loadData(adapterCountry = adapterCountry)
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


}