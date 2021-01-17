package com.ryccoatika.cryptocurrencypricelisting.core.domain.usecase

import com.ryccoatika.cryptocurrencypricelisting.core.data.Resource
import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.Cryptocurrency
import com.ryccoatika.cryptocurrencypricelisting.core.domain.repository.ICryptocurrencyRepository
import kotlinx.coroutines.flow.Flow

class CryptocurrencyInteractor(
    private val cryptocurrencyRepository: ICryptocurrencyRepository
) : CryptocurrencyUseCase {
    override fun getAllCoins(start: Int, limit: Int): Flow<Resource<Cryptocurrency>> =
        cryptocurrencyRepository.getAllCoins(start, limit)
}