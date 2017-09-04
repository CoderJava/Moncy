package com.ysn.moncy.view.submenu.available

import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.available.adapter.AdapterAvailableCurrency

/**
 * Created by root on 30/07/17.
 */
interface AvailableCurrencyView : MvpView {

    /**
     * Method interface load data available currency failed
     * @param isRefresh
     * Indicator that this method interface is called for refresh or not
     */
    fun loadDataFailed(isRefresh: Boolean)

    /**
     * Method interface load data available currency success
     * @param adapterAvailableCurrency
     * Adapter for recycler view
     * @param isRefresh
     * Indicator that this method interface is called for refresh or not
     */
    fun  loadData(adapterAvailableCurrency: AdapterAvailableCurrency, isRefresh: Boolean)

}