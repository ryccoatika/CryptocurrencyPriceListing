package com.ryccoatika.cryptocurrencypricelisting.core.domain.usecase

import com.ryccoatika.cryptocurrencypricelisting.core.data.Resource
import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.Cryptocurrency
import kotlinx.coroutines.flow.Flow

interface CryptocurrencyUseCase {
    fun getAllCoins(start: Int, limit: Int): Flow<Resource<List<Cryptocurrency>>>
}