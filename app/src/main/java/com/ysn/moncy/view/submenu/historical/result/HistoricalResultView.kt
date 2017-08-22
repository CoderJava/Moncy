package com.ysn.moncy.view.submenu.historical.result

import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.historical.result.adapter.AdapterHistoricalResult

/**
 * Created by root on 22/08/17.
 */
interface HistoricalResultView : MvpView {

    fun loadData(adapterHistoricalResult: AdapterHistoricalResult)

}