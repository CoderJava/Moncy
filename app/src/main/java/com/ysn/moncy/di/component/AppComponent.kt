package com.ysn.moncy.di.component

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ysn.moncy.api.country.CountryEndpoints
import com.ysn.moncy.api.countrycurrency.CountryCurrencyEndpoints
import com.ysn.moncy.api.currency.CurrencyEndpoints
import com.ysn.moncy.api.currencyconverter.CurrencyConverterEndpoints
import com.ysn.moncy.api.fixer.FixerEndpoints
import com.ysn.moncy.di.module.*
import dagger.Component
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (RetrofitModule::class), (CurrencyApiModule::class), (FixerApiModule::class), (CountryApiModule::class), (CurrencyConverterApiModule::class), (CountryCurrencyApiModule::class), (OkHttpModule::class), (SharedPreferencesModule::class)])
interface AppComponent {

    fun application(): Application

    fun gson(): Gson

    @Named("currency")
    fun retrofitCurrency(): Retrofit

    @Named("fixer")
    fun retrofitFixer(): Retrofit

    @Named("country")
    fun retrofitCountry(): Retrofit

    @Named("currencyconverter")
    fun retrofitCurrencyConverter(): Retrofit

    @Named("countrycurrency")
    fun retrofitCountryCurrency(): Retrofit

    fun currencyEndpoints(): CurrencyEndpoints

    fun fixerEndpoints(): FixerEndpoints

    fun countryEndpoints(): CountryEndpoints

    fun currencyConverterEndpoints(): CurrencyConverterEndpoints

    fun countryCurrencyEndpoints(): CountryCurrencyEndpoints

    fun cache(): Cache

    fun client(): OkHttpClient

    fun loggingInterceptor(): HttpLoggingInterceptor

    fun sharedPreferences(): SharedPreferences

}