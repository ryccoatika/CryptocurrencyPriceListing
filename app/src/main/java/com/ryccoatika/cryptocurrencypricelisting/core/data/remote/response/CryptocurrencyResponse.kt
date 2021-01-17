package com.ryccoatika.cryptocurrencypricelisting.core.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptocurrencyResponse(
    @Json(name = "data")
    val data: List<CryptocurrencyDataResponse>?,
    @Json(name = "info")
    val info: InfoResponse?
) {
    @JsonClass(generateAdapter = true)
    data class InfoResponse(
        @Json(name = "coins_num")
        val coinsNum: Int?
    )

    @JsonClass(generateAdapter = true)
    data class CryptocurrencyDataResponse(
        @Json(name = "id")
        val id: String?,
        @Json(name = "symbol")
        val symbol: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "nameid")
        val nameId: String?,
        @Json(name = "rank")
        val rank: Int?,
        @Json(name = "price_usd")
        val priceUsd: String?,
        @Json(name = "percent_change_24h")
        val percentChange24h: String?,
        @Json(name = "percent_change_1h")
        val percentChange1h: String?,
        @Json(name = "percent_change_7d")
        val percentChange7d: String?,
        @Json(name = "price_btc")
        val priceBtc: String?,
        @Json(name = "market_cap_usd")
        val marketCapUsd: String?,
        @Json(name = "volume24")
        val volume24: Double?,
        @Json(name = "volume24a")
        val volume24a: Double?,
        @Json(name = "csupply")
        val cSupply: String?,
        @Json(name = "tsupply")
        val tSupply: String?,
        @Json(name = "msupply")
        val mSupply: String?
    )
}