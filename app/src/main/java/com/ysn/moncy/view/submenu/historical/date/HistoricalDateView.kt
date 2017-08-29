package com.ysn.moncy.view.submenu.historical.date

import com.ysn.moncy.model.merge.historical.MergeHistorical
import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 20/08/17.
 */
interface HistoricalDateView : MvpView {

    fun submit(strSourceHistorical: String, strDateHistorical: String, listMergeHistorical: ArrayList<MergeHistorical>)

    fun submitFailed()

}