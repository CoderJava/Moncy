package com.ysn.moncy.view.submenu.available

import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 30/07/17.
 */
class AvailableCurrencyPresenter : MvpPresenter<AvailableCurrencyView> {

    private val TAG = javaClass.simpleName
    private var availableCurrencyView: AvailableCurrencyView? = null

    override fun onAttach(mvpView: MvpView) {
        availableCurrencyView = mvpView as AvailableCurrencyView
    }

    override fun onDetach() {
        availableCurrencyView = null
    }
}