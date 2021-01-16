package com.ryccoatika.cryptocurrencypricelisting.core.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCryptocurrencyResponse(
    @Json(name = "data")
    val data: List<CryptocurrencyResponse?>
)