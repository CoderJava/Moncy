package com.ysn.moncy.views.ui.activity.currencynow

import com.ysn.moncy.views.base.mvp.BaseView
import com.ysn.moncy.views.ui.activity.currencynow.adapter.AdapterCurrencyNow

interface CurrencyNowView : BaseView {

    fun loadData(adapterCurrencyNow: AdapterCurrencyNow, datetimeFormatting: String, source: String)

    fun loadDataFailed(message: String?)

}
