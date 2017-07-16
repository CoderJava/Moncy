package com.ysn.moncy.model.trend

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("net.hexar.json2pojo")
class CurrencyNow (

    @SerializedName("privacy")
    var privacy: String,
    @SerializedName("quotes")
    var quotes: Quotes,
    @SerializedName("source")
    var source: String,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("terms")
    var terms: String,
    @SerializedName("timestamp")
    var timestamp: Long

)
