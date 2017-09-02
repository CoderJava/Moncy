package com.ysn.moncy.model.country

/**
 * Created by root on 23/07/17.
 * Data Model Country
 */
data class Country(
        var region: String? = null,
        var latlng: List<Double>? = null,
        var flag: String,
        var name: String,
        var currencies: List<Currencies>
)