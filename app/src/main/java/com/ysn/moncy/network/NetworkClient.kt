package com.ysn.moncy.network

import com.ysn.moncy.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by root on 21/07/17.
 */
object NetworkClient {

    object RetrofitCurrency {
        private var retrofitCurrency: Retrofit? = null

        fun getRetrofitCurrency(): Retrofit? {
            if (retrofitCurrency == null) {
                val builder = OkHttpClient.Builder()
                val okHttpClient = builder.build()
                retrofitCurrency = Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL_CURRENCY)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient)
                        .build()
            }
            return retrofitCurrency
        }
    }

    object RetrofitCountry {
        private var retrofitCountry: Retrofit? = null

        fun getRetrofitCountry(): Retrofit? {
            if (retrofitCountry == null) {
                val builder = OkHttpClient.Builder()
                val okHttpClient = builder.build()
                retrofitCountry = Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL_COUNTRY)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient)
                        .build()
            }
            return retrofitCountry
        }
    }

}