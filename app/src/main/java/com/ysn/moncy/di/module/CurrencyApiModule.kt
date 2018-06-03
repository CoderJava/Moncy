package com.ysn.moncy.di.module

import com.ysn.moncy.api.currency.CurrencyEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class CurrencyApiModule {

    @Provides
    @Singleton
    fun provideEndpoints(@Named("currency") retrofit: Retrofit): CurrencyEndpoints = retrofit.create(CurrencyEndpoints::class.java)

}