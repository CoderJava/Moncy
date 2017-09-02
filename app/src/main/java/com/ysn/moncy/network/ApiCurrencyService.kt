package com.ysn.moncy.network

import com.ysn.moncy.model.currency.available.AvailableCurrency
import com.ysn.moncy.model.currency.historical.Historical
import com.ysn.moncy.model.currency.live.CurrencyNow
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by root on 21/07/17.
 * Api Currency Service
 */
interface ApiCurrencyService {

    /**
     * @param accessKey
     * API Key for http://apilayer.net/api/
     */
    @GET("live")
    fun getLive(
            @Query("access_key") accessKey: String
    ): Observable<CurrencyNow>

    /**
     * @param accessKey
     * API Key for http://apilayer.net/api/
     */
    @GET("list")
    fun getAvailableCurrency(
            @Query("access_key") accessKey: String
    ): Observable<AvailableCurrency>

    /**
     * @param accessKey
     * API Key for http://apilayer.net/api/
     * @param date
     * History date currency
     */
    @GET("historical")
    fun getHistoricalCurrencies(
            @Query("access_key") accessKey: String,
            @Query("date") date: String
    ): Observable<Historical>

}