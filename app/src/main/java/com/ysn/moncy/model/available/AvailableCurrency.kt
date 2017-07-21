package com.ysn.moncy.model.available

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("net.hexar.json2pojo")
data class AvailableCurrency (

    @SerializedName("currencies")
    var currencies: Currencies? = null,
    @SerializedName("privacy")
    var privacy: String? = null,
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("terms")
    var terms: String? = null

)
