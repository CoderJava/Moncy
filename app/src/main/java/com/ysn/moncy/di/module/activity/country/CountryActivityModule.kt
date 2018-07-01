package com.ysn.moncy.di.module.activity.country

import com.ysn.moncy.api.country.CountryEndpoints
import com.ysn.moncy.api.countrycurrency.CountryCurrencyEndpoints
import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.views.ui.activity.country.CountryPresenter
import dagger.Module
import dagger.Provides

@Module
class CountryActivityModule {

    @ActivityScope
    @Provides
    internal fun provideCountryPresenter(countryEndpoints: CountryEndpoints, countryCurrencyEndpoints: CountryCurrencyEndpoints):
            CountryPresenter = CountryPresenter(countryEndpoints = countryEndpoints, countryCurrencyEndpoints = countryCurrencyEndpoints)

}