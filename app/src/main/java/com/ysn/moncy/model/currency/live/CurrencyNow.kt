package com.ysn.moncy.model.currency.live

/**
 * Created by root on 23/07/17.
 */
data class CurrencyNow (
        var success: Boolean,
        var terms: String,
        var privacy: String,
        var timestamp: Long,
        var source: String,
        var quotes: Map<String, Double>
)