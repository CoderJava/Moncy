package com.ysn.moncy.di.component.activity.country

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.di.component.AppComponent
import com.ysn.moncy.di.module.activity.country.CountryActivityModule
import com.ysn.moncy.views.ui.activity.country.CountryActivity
import dagger.Component

@ActivityScope
@Component(dependencies = ([AppComponent::class]), modules = [(CountryActivityModule::class)])
interface CountryActivityComponent {

    fun inject(countryActivity: CountryActivity)

}