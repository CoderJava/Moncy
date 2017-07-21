package com.ysn.moncy.network

import com.ysn.moncy.model.available.AvailableCurrency
import com.ysn.moncy.model.history.HistoricalCurrency
import com.ysn.moncy.model.live.CurrencyNow
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by root on 21/07/17.
 */
interface NetworkCurrencies {

    @GET("live")
    fun getLive(
            @Query("access_key") accessKey: String
    ): Observable<CurrencyNow>


    @GET("list")
    fun getAvailableCurrency(
            @Query("access_key") accessKey: String
    ): Observable<AvailableCurrency>

    @GET("historical")
    fun getHistoricalCurrencies(
            @Query("access_key") accessKey: String,
            @Query("date") date: String
    ): Observable<HistoricalCurrency>

    @GET("live")
    fun getSpecifyOutputCurrencies(
            @Query("access_key") accessKey: String,
            @Query("currencies") currencies: String
    ): Observable<ResponseBody>

}