package com.ryccoatika.cryptocurrencypricelisting.core.data

import com.ryccoatika.cryptocurrencypricelisting.core.data.mappers.toCryptocurrencyDomain
import com.ryccoatika.cryptocurrencypricelisting.core.data.remote.ApiResponse
import com.ryccoatika.cryptocurrencypricelisting.core.data.remote.ICryptocurrencyDataSource
import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.Cryptocurrency
import com.ryccoatika.cryptocurrencypricelisting.core.domain.repository.ICryptocurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptocurrencyRepository(
    private val cryptocurrencyDataSource: ICryptocurrencyDataSource
) : ICryptocurrencyRepository {
    override fun getAllCoins(start: Int, limit: Int): Flow<Resource<Cryptocurrency>> =
        flow {
            emit(Resource.InProgress)
            when(val response = cryptocurrencyDataSource.getAllCoins(start, limit)) {
                is ApiResponse.Success -> emit(Resource.Success(response.data.data.map { it.toCryptocurrencyDomain() }))
                is ApiResponse.Error -> emit(Resource.Error(response.message))
                ApiResponse.Empty -> emit(Resource.Empty)
            }
        }
}