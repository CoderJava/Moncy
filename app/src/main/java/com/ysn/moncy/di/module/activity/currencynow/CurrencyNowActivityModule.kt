package com.ysn.moncy.di.module.activity.currencynow

import com.ysn.moncy.api.currency.CurrencyEndpoints
import com.ysn.moncy.api.currencyconverter.CurrencyConverterEndpoints
import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.views.ui.activity.currencynow.CurrencyNowPresenter
import dagger.Module
import dagger.Provides

@Module
class CurrencyNowActivityModule {

    @ActivityScope
    @Provides
    internal fun provideCurrencyNowPresenter(currencyEndpoints: CurrencyEndpoints, currencyConverterEndpoints: CurrencyConverterEndpoints):
            CurrencyNowPresenter = CurrencyNowPresenter(currencyEndpoints = currencyEndpoints, currencyConverterEndpoints = currencyConverterEndpoints)

}