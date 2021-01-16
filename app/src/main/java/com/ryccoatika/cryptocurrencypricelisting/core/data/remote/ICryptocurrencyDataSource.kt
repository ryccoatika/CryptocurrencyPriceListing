package com.ryccoatika.cryptocurrencypricelisting.core.data.remote

import com.ryccoatika.cryptocurrencypricelisting.core.data.remote.response.GetCryptocurrencyResponse

interface ICryptocurrencyDataSource {
    suspend fun getAllCoins(start: Int, limit: Int): ApiResponse<GetCryptocurrencyResponse>
}