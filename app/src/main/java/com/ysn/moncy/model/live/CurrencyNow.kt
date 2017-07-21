package com.ysn.moncy.model.live

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("net.hexar.json2pojo")
data class CurrencyNow(

        @SerializedName("privacy")
        var privacy: String = "",
        @SerializedName("quotes")
        var quotesCurrencyNow: QuotesCurrencyNow = QuotesCurrencyNow(),
        @SerializedName("source")
        var source: String = "",
        @SerializedName("success")
        var success: Boolean = true,
        @SerializedName("terms")
        var terms: String = "",
        @SerializedName("timestamp")
        var timestamp: Long = 0L

)
