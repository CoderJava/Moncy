package com.ysn.moncy.network

import com.ysn.moncy.model.currency.convert.ConvertCurrency
import com.ysn.moncy.model.currency.convert.CurrencyFixer
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by root on 23/08/17.
 */
interface ApiFixerService {

    @GET("latest")
    fun getListCurrency(): Observable<CurrencyFixer>

    @GET("latest")
    fun getSpecifiedCurrency(
            @Query("base") base: String,
            @Query("symbols") symbols: String
    ): Observable<ConvertCurrency>


}