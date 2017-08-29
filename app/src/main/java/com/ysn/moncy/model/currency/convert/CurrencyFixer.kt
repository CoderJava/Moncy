package com.ysn.moncy.model.currency.convert

/**
 * Created by root on 26/08/17.
 */

data class CurrencyFixer (
        var base: String,
        var date: String,
        var rates: Map<String, Double>
)