package com.ysn.moncy.model.merge.live

import android.graphics.Bitmap

/**
 * Created by root on 23/07/17.
 */
data class MergeLive (
        var label: String,
        var value: String,
        var countryName: String,
        var flag: String,
        var bitmapFlag: Bitmap? = null
)