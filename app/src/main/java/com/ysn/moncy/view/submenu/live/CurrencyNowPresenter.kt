package com.ysn.moncy.view.submenu.live

import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 21/07/17.
 */
class CurrencyNowPresenter : MvpPresenter<CurrencyNowView> {

    private val TAG = javaClass.simpleName
    private var currencyNowView: CurrencyNowView? = null

    override fun onAttach(mvpView: MvpView) {
        currencyNowView = mvpView as CurrencyNowView
    }

    override fun onDetach() {
        currencyNowView = null
    }

    fun onLoadData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}