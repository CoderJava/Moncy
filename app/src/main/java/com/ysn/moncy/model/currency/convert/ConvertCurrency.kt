package com.ysn.moncy.model.currency.convert

/**
 * Created by root on 23/08/17.
 */
data class ConvertCurrency (
        var base: String,
        var date: String,
        var rates: Map<String, Double>
)