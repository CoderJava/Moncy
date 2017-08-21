package com.ysn.moncy.model.merge.live

import android.graphics.Bitmap

/**
 * Created by root on 21/08/17.
 */
data class MergeHistorical (
        var label: String,
        val value: String,
        var countryName: String,
        var flag: String,
        var bitmapFlag: Bitmap? = null
)