package com.ysn.moncy.di.module.activity.historicalcurrency

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.views.ui.activity.historicalcurrency.HistoricalCurrencyPresenter
import dagger.Module
import dagger.Provides

@Module
class HistoricalCurrencyActivityModule {

    @ActivityScope
    @Provides
    internal fun provideHistoricalCurrencyPresenter(): HistoricalCurrencyPresenter = HistoricalCurrencyPresenter()

}