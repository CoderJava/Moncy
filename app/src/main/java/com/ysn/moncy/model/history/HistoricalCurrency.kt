package com.ysn.moncy.model.history

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("net.hexar.json2pojo")
data class HistoricalCurrency(

        @SerializedName("date")
        var date: String? = null,
        @SerializedName("historical")
        var historical: Boolean? = null,
        @SerializedName("privacy")
        var privacy: String? = null,
        @SerializedName("quotes")
        var quotesHistoricalCurrency: QuotesHistoricalCurrency? = null,
        @SerializedName("source")
        var source: String? = null,
        @SerializedName("success")
        var success: Boolean? = null,
        @SerializedName("terms")
        var terms: String? = null,
        @SerializedName("timestamp")
        var timestamp: Long? = null

)
