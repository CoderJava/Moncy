package com.ysn.moncy.view.submenu.live

import com.ysn.moncy.model.currency.live.CurrencyNow
import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.main.adapter.AdapterCurrencyNow

/**
 * Created by root on 21/07/17.
 */
interface CurrencyNowView : MvpView {

    fun loadData(adapterCurrencyNow: AdapterCurrencyNow, currencyNow: CurrencyNow?, isRefresh: Boolean)

    fun loadDataFailed(isRefresh: Boolean)


}