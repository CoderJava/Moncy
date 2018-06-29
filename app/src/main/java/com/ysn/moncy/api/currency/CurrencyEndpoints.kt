package com.ysn.moncy.api.currency

import com.ysn.moncy.BuildConfig
import com.ysn.moncy.model.currencynow.CurrencyNow
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyEndpoints {

    /* http://apilayer.net/api/live?access_key=257cb471003f7806c5706d137f67a338 */
    @GET("live")
    fun getLiveCurrency(@Query("access_key") accessKey: String = BuildConfig.API_KEY_CURRENCY): Observable<CurrencyNow>

}