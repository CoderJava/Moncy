package com.ysn.moncy.di.component.activity.historicalcurrency

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.di.component.AppComponent
import com.ysn.moncy.di.module.activity.historicalcurrency.HistoricalCurrencyActivityModule
import com.ysn.moncy.views.ui.activity.historicalcurrency.HistoricalCurrencyActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(HistoricalCurrencyActivityModule::class)])
interface HistoricalCurrencyActivityComponent {

    fun inject(historicalCurrencyActivity: HistoricalCurrencyActivity)

}