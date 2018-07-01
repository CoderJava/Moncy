package com.ysn.moncy.api.country

import com.ysn.moncy.model.country.CountryData
import io.reactivex.Observable
import retrofit2.http.GET

interface CountryEndpoints {

    @GET("rest/v2")
    fun getCountries(): Observable<List<CountryData>>
}