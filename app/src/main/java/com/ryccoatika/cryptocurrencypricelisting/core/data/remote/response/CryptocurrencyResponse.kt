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
        val priceUsd: String?
    )
}