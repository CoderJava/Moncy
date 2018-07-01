package com.ysn.moncy.di.module.activity.country

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.views.ui.activity.country.CountryPresenter
import dagger.Module
import dagger.Provides

@Module
class CountryActivityModule {

    @ActivityScope
    @Provides
    internal fun provideCountryPresenter(): CountryPresenter = CountryPresenter()

}