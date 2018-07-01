package com.ysn.moncy.model.country

import com.google.gson.annotations.SerializedName

data class CountryData(
        @SerializedName("name") val name: String,
        @SerializedName("topLevelDomain") val topLevelDomain: List<String>,
        @SerializedName("alpha2Code") val alpha2Code: String,
        @SerializedName("alpha3Code") val alpha3Code: String,
        @SerializedName("callingCodes") val callingCodes: List<String>,
        @SerializedName("capital") val capital: String,
        @SerializedName("altSpellings") val altSpellings: List<String>,
        @SerializedName("region") val region: String,
        @SerializedName("subregion") val subregion: String,
        @SerializedName("population") val population: Int,
        @SerializedName("latlng") val latlng: List<Double>,
        @SerializedName("demonym") val demonym: String,
        @SerializedName("area") val area: Double,
        @SerializedName("gini") val gini: Any,
        @SerializedName("timezones") val timezones: List<String>,
        @SerializedName("borders") val borders: List<String>,
        @SerializedName("nativeName") val nativeName: String,
        @SerializedName("numericCode") val numericCode: String,
        @SerializedName("currencies") val currencies: List<Currency>,
        @SerializedName("languages") val languages: List<Language>,
        @SerializedName("translations") val translations: Translations,
        @SerializedName("flag") val flag: String,
        @SerializedName("regionalBlocs") val regionalBlocs: List<RegionalBloc>,
        @SerializedName("cioc") val cioc: String
)

data class RegionalBloc(
        @SerializedName("acronym") val acronym: String,
        @SerializedName("name") val name: String,
        @SerializedName("otherAcronyms") val otherAcronyms: List<Any>,
        @SerializedName("otherNames") val otherNames: List<String>
)

data class Currency(
        @SerializedName("code") val code: String,
        @SerializedName("name") val name: Any,
        @SerializedName("symbol") val symbol: Any
)

data class Translations(
        @SerializedName("de") val de: String,
        @SerializedName("es") val es: String,
        @SerializedName("fr") val fr: String,
        @SerializedName("ja") val ja: String,
        @SerializedName("it") val it: String,
        @SerializedName("br") val br: String,
        @SerializedName("pt") val pt: String,
        @SerializedName("nl") val nl: String,
        @SerializedName("hr") val hr: String,
        @SerializedName("fa") val fa: String
)

data class Language(
        @SerializedName("iso639_1") val iso6391: String,
        @SerializedName("iso639_2") val iso6392: String,
        @SerializedName("name") val name: String,
        @SerializedName("nativeName") val nativeName: String
)