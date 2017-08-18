package com.ysn.moncy.view.submenu.available

import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.available.adapter.AdapterAvailableCurrency

/**
 * Created by root on 30/07/17.
 */
interface AvailableCurrencyView : MvpView {

    fun loadDataFailed(refresh: Boolean)

    fun  loadData(adapterAvailableCurrency: AdapterAvailableCurrency, refresh: Boolean)

}