package com.ysn.moncy.network

import com.ysn.moncy.model.country.Country
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.ResponseBody
import org.intellij.lang.annotations.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by root on 23/07/17.
 * API Country Service
 */
interface ApiCountryService {

    /**
     * @param fields
     * Fields about data country ex: name, region and etc
     */
    @GET("all")
    fun getDataCountry(
            @Query("fields") fields: String
    ): Observable<List<Country>>

}