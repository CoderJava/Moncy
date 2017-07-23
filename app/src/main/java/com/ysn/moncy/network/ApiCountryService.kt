package com.ysn.moncy.network

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by root on 23/07/17.
 */
interface ApiCountryService {

    @GET("alpha")
    fun getDataCountry(
            @Path("code") code: String
    ): Observable<ResponseBody>

}