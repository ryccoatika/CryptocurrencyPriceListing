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
        priceUsd = this?.priceUsd ?: "",
        percentChange24h = this?.percentChange24h ?: "",
        percentChange1h = this?.percentChange1h ?: "",
        percentChange7d = this?.percentChange7d ?: "",
        priceBtc = this?.priceBtc ?: "",
        marketCapUsd = this?.marketCapUsd ?: "",
        volume24 = this?.volume24 ?: 0.0,
        volume24a = this?.volume24a ?: 0.0,
        cSupply = this?.cSupply ?: "",
        tSupply = this?.tSupply ?: "",
        mSupply = this?.mSupply ?: ""
    )