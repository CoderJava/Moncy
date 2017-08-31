package com.ysn.moncy.view.submenu.convert.choosecurrency

import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.convert.choosecurrency.adapter.AdapterChooseConvertCurrency

/**
 * Created by root on 31/08/17.
 */
interface ChooseConvertCurrencyView : MvpView {

    fun loadData(adapterChooseConvertCurrency: AdapterChooseConvertCurrency)

}