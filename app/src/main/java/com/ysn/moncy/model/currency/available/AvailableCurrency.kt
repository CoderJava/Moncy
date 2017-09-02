package com.ysn.moncy.model.currency.available

/**
 * Created by root on 02/08/17.
 * Data Model AvailableCurrency
 */
data class AvailableCurrency(
        var success: Boolean,
        var terms: String,
        var privacy: String,
        var currencies: Map<String, String>
)