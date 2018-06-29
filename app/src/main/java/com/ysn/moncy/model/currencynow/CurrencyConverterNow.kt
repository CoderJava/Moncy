package com.ysn.moncy.model.currencynow

import com.google.gson.annotations.SerializedName

data class CurrencyConverterNow(
        @SerializedName("query") val query: Query,
        @SerializedName("results") val results: Map<String, Result>
)

data class Result(
        @SerializedName("id") val id: String = "",
        @SerializedName("val") val valX: String = "0",
        @SerializedName("to") val to: String = "",
        @SerializedName("fr") val fr: String = ""
)

data class Query(
        @SerializedName("count") val count: Int
)