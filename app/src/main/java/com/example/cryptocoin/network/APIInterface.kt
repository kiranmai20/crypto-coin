package com.example.cryptocoin.network

import com.example.cryptocoin.model.CryptoList
import com.example.cryptocoin.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {
    @Headers("X-CMC_PRO_API_KEY: 3014e691-d2cc-4f05-8c94-cc43c17c0cae")
    @GET("/v1/cryptocurrency/listings/latest?")
    fun doGetUserList(@Query("limit") page: String?): Call<CryptoList?>?

    // https://pro-api.coinmarketcap.com/v1/cryptocurrency/info
    @Headers("X-CMC_PRO_API_KEY: 3014e691-d2cc-4f05-8c94-cc43c17c0cae")
    @GET("/v1/cryptocurrency/info")
    fun getCoinById(@Query("id") id: Int): Call<DataResponse?>?
}