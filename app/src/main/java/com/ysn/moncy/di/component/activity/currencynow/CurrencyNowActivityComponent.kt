package com.ysn.moncy.di.component.activity.currencynow

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.di.component.AppComponent
import com.ysn.moncy.di.module.activity.currencynow.CurrencyNowActivityModule
import com.ysn.moncy.views.ui.activity.currencynow.CurrencyNowActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(CurrencyNowActivityModule::class)])
interface CurrencyNowActivityComponent {

    fun inject(currencyNowActivity: CurrencyNowActivity)

}