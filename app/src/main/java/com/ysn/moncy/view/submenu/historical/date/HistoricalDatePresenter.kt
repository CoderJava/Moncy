package com.ysn.moncy.view.submenu.historical.date

import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 20/08/17.
 */
class HistoricalDatePresenter : MvpPresenter<HistoricalDateView> {

    private val TAG = javaClass.simpleName
    private var historicalDateView: HistoricalDateView? = null

    override fun onAttach(mvpView: MvpView) {
        historicalDateView = mvpView as HistoricalDateView
    }

    override fun onDetach() {
        historicalDateView = null
    }

}