package com.ysn.moncy.di.module

import com.ysn.moncy.api.countrycurrency.CountryCurrencyEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class CountryCurrencyApiModule {

    @Provides
    @Singleton
    fun provideEndpoints(@Named("countrycurrency") retrofit: Retrofit): CountryCurrencyEndpoints = retrofit.create(CountryCurrencyEndpoints::class.java)

}