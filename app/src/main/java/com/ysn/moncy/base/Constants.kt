package com.ysn.moncy.base

import android.support.annotation.IntDef

/**
 * Created by root on 15/07/17.
 */

object Constants {

    @IntDef(
            REQUEST_NONE,
            REQUEST_RUNNING,
            REQUEST_SUCCEEDED,
            REQUEST_FAILED
    )
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class RequestState

    const val REQUEST_NONE = 0L
    const val REQUEST_RUNNING = 1L
    const val REQUEST_SUCCEEDED = 2L
    const val REQUEST_FAILED = 3L

}
