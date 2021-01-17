package com.ryccoatika.cryptocurrencypricelisting.core.data.mappers

import com.ryccoatika.cryptocurrencypricelisting.core.data.remote.response.CryptocurrencyResponse
import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.Cryptocurrency

fun CryptocurrencyResponse?.toCryptocurrencyDomain(): Cryptocurrency =
    Cryptocurrency(
        data = this?.data?.map { it.toCryptocurrencyDataDomain() } ?: emptyList(),
        info = this?.info.toCryptocurrencyInfoDomain()
    )

fun CryptocurrencyResponse.InfoResponse?.toCryptocurrencyInfoDomain(): Cryptocurrency.Info =
    Cryptocurrency.Info(
        coinsNum = this?.coinsNum ?: 0
    )

fun CryptocurrencyResponse.CryptocurrencyDataResponse?.toCryptocurrencyDataDomain(): Cryptocurrency.CryptocurrencyData =
    Cryptocurrency.CryptocurrencyData(
        id = this?.id ?: "",
        symbol = this?.symbol ?: "",
        name = this?.name ?: "",
        nameId = this?.nameId ?: "",
        rank = this?.rank ?: 0,
        priceUsd = this?.priceUsd ?: ""
    )