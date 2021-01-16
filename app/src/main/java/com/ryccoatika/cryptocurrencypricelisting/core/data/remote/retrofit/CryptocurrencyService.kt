package com.ryccoatika.cryptocurrencypricelisting.core.data.remote.retrofit

import com.ryccoatika.cryptocurrencypricelisting.core.data.remote.response.GetCryptocurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptocurrencyService {
    @GET("/api/tickers/")
    suspend fun getAllCoins(
        @Query("start")
        start: Int,
        @Query("limit")
        limit: Int
    ): GetCryptocurrencyResponse
}