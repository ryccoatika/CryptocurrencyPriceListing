package com.ryccoatika.cryptocurrencypricelisting.core.domain.repository

import com.ryccoatika.cryptocurrencypricelisting.core.data.Resource
import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.Cryptocurrency
import kotlinx.coroutines.flow.Flow

interface ICryptocurrencyRepository {
    fun getAllCoins(start: Int, limit: Int): Flow<Resource<List<Cryptocurrency>>>
}