package com.ryccoatika.cryptocurrencypricelisting.core.domain.model

data class Cryptocurrency(
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