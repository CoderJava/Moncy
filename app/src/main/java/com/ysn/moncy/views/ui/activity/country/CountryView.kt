package com.ysn.moncy.views.ui.activity.country

import android.content.Context
import com.ysn.moncy.views.base.mvp.BaseView
import com.ysn.moncy.views.ui.activity.country.adapter.AdapterCountry

interface CountryView : BaseView {

    fun getViewContext(): Context

    fun loadDataFailed(message: String?)

    fun loadData(adapterCountry: AdapterCountry)

}
