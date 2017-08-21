package com.ysn.moncy.model.currency.historical

/**
 * Created by root on 20/08/17.
 */
data class Historical (
        var success: Boolean,
        var terms: String,
        var privacy: String,
        var historical: Boolean,
        var date: String,
        var timestamp: Long,
        var source: String,
        var quotes: Map<String, Double>
)