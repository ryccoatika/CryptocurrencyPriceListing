package com.ryccoatika.cryptocurrencypricelisting.ui.di

import com.ryccoatika.cryptocurrencypricelisting.core.domain.usecase.CryptocurrencyInteractor
import com.ryccoatika.cryptocurrencypricelisting.core.domain.usecase.CryptocurrencyUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<CryptocurrencyUseCase> { CryptocurrencyInteractor(get()) }
}