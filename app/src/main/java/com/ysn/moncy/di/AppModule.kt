package com.ysn.moncy.di

import com.ysn.moncy.BuildConfig
import com.ysn.moncy.api.country.CountryEndpoints
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideCountryEndpoints(): CountryEndpoints {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_COUNTRY)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(CountryEndpoints::class.java)
    }

}