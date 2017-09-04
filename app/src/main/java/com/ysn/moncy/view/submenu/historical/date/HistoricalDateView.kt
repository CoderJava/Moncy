package com.ysn.moncy.view.submenu.historical.date

import com.ysn.moncy.model.merge.historical.MergeHistorical
import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 20/08/17.
 */
interface HistoricalDateView : MvpView {

    /**
     * Method interface submit data success to get data history currency
     * @param strSourceHistorical
     * Source currency code
     * @param strDateHistorical
     * History date currency
     * @param listMergeHistorical
     * List data merge historical
     */
    fun submit(strSourceHistorical: String, strDateHistorical: String,
               listMergeHistorical: ArrayList<MergeHistorical>)

    /**
     * Method interface submit data failed to get data history currency
     */
    fun submitFailed()

}