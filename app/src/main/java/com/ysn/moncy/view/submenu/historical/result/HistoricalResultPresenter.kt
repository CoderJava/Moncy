package com.ysn.moncy.view.submenu.historical.result

import android.content.Context
import android.util.Log
import com.ysn.moncy.model.merge.historical.MergeHistorical
import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.historical.result.adapter.AdapterHistoricalResult

/**
 * Created by root on 22/08/17.
 */
class HistoricalResultPresenter : MvpPresenter<HistoricalResultView> {

    private val TAG = javaClass.simpleName
    private var historicalResultView: HistoricalResultView? = null

    /**
     * On attach view
     * @param mvpView
     * View historical result view
     */
    override fun onAttach(mvpView: HistoricalResultView) {
        historicalResultView = mvpView
    }

    /**
     * On detach view
     */
    override fun onDetach() {
        historicalResultView = null
    }

    /**
     * On load data historical currency to setup data adapter
     * @param context
     * Context
     * @param listMergeHistorical
     * List data merge historical
     */
    fun onLoadData(context: Context, listMergeHistorical: ArrayList<MergeHistorical>) {
        val adapterHistoricalResult = AdapterHistoricalResult(
                context = context,
                listMergeHistorical = listMergeHistorical
        )
        historicalResultView?.loadData(adapterHistoricalResult)
    }
}