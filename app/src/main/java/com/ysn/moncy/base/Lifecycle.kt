package com.ysn.moncy.base

/**
 * Created by root on 15/07/17.
 */

interface Lifecycle {

    interface View

    interface ViewModel {

        fun onViewResumed()

        fun onViewAttached(viewCallback: Lifecycle.View)

        fun onViewDetached()

    }

}
