package com.ryccoatika.cryptocurrencypricelisting.core.domain.model

import java.text.NumberFormat
import java.util.*

data class Cryptocurrency(
    val data: List<CryptocurrencyData>,
    val info: Info
) {
    data class Info(
        val coinsNum: Int
    )
    data class CryptocurrencyData(
        val id: String,
        val symbol: String,
        val name: String,
        val nameId: String,
        val rank: Int,
        val priceUsd: String,
        val percentChange24h: String,
        val percentChange1h: String,
        val percentChange7d: String,
        val priceBtc: String,
        val marketCapUsd: String,
        val volume24: Double,
        val volume24a: Double,
        val cSupply: String,
        val tSupply: String,
        val mSupply: String
    )
}

val Cryptocurrency.CryptocurrencyData.imageUrl
    get() = "https://www.coinlore.com/img/$nameId.png"

val Cryptocurrency.CryptocurrencyData.formattedPriceUsd
    get() = NumberFormat.getCurrencyInstance(Locale.US).format(priceUsd.toDoubleOrNull() ?: 0.0)