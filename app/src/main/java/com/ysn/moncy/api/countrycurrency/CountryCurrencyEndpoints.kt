package com.ysn.moncy.api.countrycurrency

import io.reactivex.Observable
import retrofit2.http.GET

interface CountryCurrencyEndpoints {

    @GET("currency.json")
    fun getCurrencyCode(): Observable<Map<String, String>>
}