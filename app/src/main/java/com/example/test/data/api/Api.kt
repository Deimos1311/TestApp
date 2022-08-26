package com.example.test.data.api

import com.example.test.BuildConfig
import com.example.test.data.dto.DataDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Api for interaction with Exchanges Rates API
 *
 * @see <a href = “https://github.com/exchangeratesapi/exchangeratesapi”>Exchanges Rates Data API</a>
 *
 * @author Evgen K.
 */
interface Api {

    /**
     * Receive data from API
     *
     * @param currency current value of the currency for the query in the string
     *
     * @return nullable [DataDto]
     */
    @Headers("apikey: ${BuildConfig.API_KEY}")
    @GET(Requests.pathLatest)
    suspend fun getAllCurrencies(@Query(Requests.queryBase) currency: String): DataDto?

}