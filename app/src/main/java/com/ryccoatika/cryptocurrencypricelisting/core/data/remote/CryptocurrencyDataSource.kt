package com.ryccoatika.cryptocurrencypricelisting.core.data.remote

import com.ryccoatika.cryptocurrencypricelisting.core.data.remote.response.GetCryptocurrencyResponse
import com.ryccoatika.cryptocurrencypricelisting.core.data.remote.retrofit.CryptocurrencyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptocurrencyDataSource(
    private val cryptocurrencyService: CryptocurrencyService
): ICryptocurrencyDataSource {
    override suspend fun getAllCoins(
        start: Int,
        limit: Int
    ): ApiResponse<GetCryptocurrencyResponse> = withContext(Dispatchers.IO) {
        try {
            val response = cryptocurrencyService.getAllCoins(start, limit)
            if (response.data.isNullOrEmpty())
                return@withContext ApiResponse.Empty
            else
                return@withContext ApiResponse.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext ApiResponse.Error(e.message.toString())
        }
    }
}