package com.ysn.moncy.di.module.activity.currencynow

import com.ysn.moncy.api.country.CountryEndpoints
import com.ysn.moncy.api.currency.CurrencyEndpoints
import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.views.ui.activity.currencynow.CurrencyNowPresenter
import dagger.Module
import dagger.Provides

@Module
class CurrencyNowActivityModule {

    @ActivityScope
    @Provides
    internal fun provideCurrencyNowPresenter(currencyEndpoints: CurrencyEndpoints, countryEndpoints: CountryEndpoints):
            CurrencyNowPresenter = CurrencyNowPresenter(currencyEndpoints = currencyEndpoints, countryEndpoints = countryEndpoints)

}