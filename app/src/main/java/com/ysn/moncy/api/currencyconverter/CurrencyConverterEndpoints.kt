package com.ysn.moncy.api.currencyconverter

import com.ysn.moncy.model.currencynow.CurrencyConverterNow
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyConverterEndpoints {

    /* http://free.currencyconverterapi.com/api/v5/convert?q=USD_IDR,IDR_USD */
    @GET("convert")
    fun getConverter(@Query("q") q: String): Observable<CurrencyConverterNow>

}