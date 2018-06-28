package com.ysn.moncy.di.module

import com.ysn.moncy.api.currencyconverter.CurrencyConverterEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class CurrencyConverterApiModule {

    @Provides
    @Singleton
    fun provideEndpoints(@Named("currencyconverter") retrofit: Retrofit): CurrencyConverterEndpoints = retrofit.create(CurrencyConverterEndpoints::class.java)

}