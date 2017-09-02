package com.ysn.moncy.network

import com.ysn.moncy.model.currency.convert.ConvertCurrency
import com.ysn.moncy.model.currency.convert.CurrencyFixer
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by root on 23/08/17.
 * API Fixer Service
 */
interface ApiFixerService {

    /**
     * Get latest data currency from http://api.fixer.io/
     */
    @GET("latest")
    fun getListCurrency(): Observable<CurrencyFixer>

    /**
     * @param base
     * Base currency code
     * @param symbols
     * Destination currency code
     */
    @GET("latest")
    fun getSpecifiedCurrency(
            @Query("base") base: String,
            @Query("symbols") symbols: String
    ): Observable<ConvertCurrency>


}