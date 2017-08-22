package com.ysn.moncy.view.submenu.historical.result

import android.content.Context
import android.util.Log
import com.ysn.moncy.model.merge.live.MergeHistorical
import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.historical.result.adapter.AdapterHistoricalResult

/**
 * Created by root on 22/08/17.
 */
class HistoricalResultPresenter : MvpPresenter<HistoricalResultView> {

    private val TAG = javaClass.simpleName
    private var historicalResultView: HistoricalResultView? = null

    override fun onAttach(mvpView: MvpView) {
        historicalResultView = mvpView as HistoricalResultView
    }

    override fun onDetach() {
        historicalResultView = null
    }

    fun onLoadData(context: Context, listMergeHistorical: ArrayList<MergeHistorical>) {
        Log.d(TAG, "onLoadData")
        val adapterHistoricalResult = AdapterHistoricalResult(
                context = context,
                listMergeHistorical = listMergeHistorical
        )
        historicalResultView?.loadData(adapterHistoricalResult)
    }
}