package com.ysn.moncy.view.submenu.live

import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 21/07/17.
 */
interface CurrencyNowView : MvpView {

    fun loadDataCurrency()

    fun loadDataCurrencyFailed()

}