package com.ysn.moncy.model.merge.available

import android.graphics.Bitmap

/**
 * Created by root on 02/08/17.
 */
data class MergeAvailable(
        var name: String,
        var codeCurrency: String,
        var region: String,
        var flag: String,
        var locationMap: Bitmap
)