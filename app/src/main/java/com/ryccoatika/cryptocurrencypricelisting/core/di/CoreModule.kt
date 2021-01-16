package com.ryccoatika.cryptocurrencypricelisting.core.di

import com.ryccoatika.cryptocurrencypricelisting.core.data.CryptocurrencyRepository
import com.ryccoatika.cryptocurrencypricelisting.core.data.remote.CryptocurrencyDataSource
import com.ryccoatika.cryptocurrencypricelisting.core.data.remote.retrofit.CryptocurrencyService
import com.ryccoatika.cryptocurrencypricelisting.core.domain.repository.ICryptocurrencyRepository
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    factory { get<Retrofit>().create(CryptocurrencyService::class.java) }

    single {
        Retrofit.Builder().apply {
            addConverterFactory(MoshiConverterFactory.create(get()))
            baseUrl("https://api.coinlore.net/")
        }.build()
    }

    single { Moshi.Builder().build() }
}

val repositoryModule = module {
    single { CryptocurrencyDataSource(get()) }
    single<ICryptocurrencyRepository> { CryptocurrencyRepository(get()) }
}