package com.ysn.moncy.di.module

import com.ysn.moncy.api.country.CountryEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class CountryApiModule {

    @Provides
    @Singleton
    fun provideEndpoints(@Named("country") retrofit: Retrofit): CountryEndpoints = retrofit.create(CountryEndpoints::class.java)

}