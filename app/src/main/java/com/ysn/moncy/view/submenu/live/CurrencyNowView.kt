package com.ysn.moncy.view.submenu.live

import com.ysn.moncy.model.currency.live.CurrencyNow
import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.live.adapter.AdapterCurrencyNow

/**
 * Created by root on 21/07/17.
 */
interface CurrencyNowView : MvpView {

    /**
     * Method interface to load data currency now success from API Service
     */
    fun loadData(adapterCurrencyNow: AdapterCurrencyNow, currencyNow: CurrencyNow?, isRefresh: Boolean)

    /**
     * Method interface to laod data currency now failed from API Service
     */
    fun loadDataFailed(isRefresh: Boolean)


}