package com.ysn.moncy.view.submenu.convert

import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 26/08/17.
 */
interface ConvertCurrencyView : MvpView {

    fun loadData(listMergeConvertCurrency: List<MergeConvertCurrency>)

    fun loadDataFailed()

    fun loadDataConverterCurrency(valueConverterCurrency: Double)

    fun loadDataConverterCurrencyFailed()

}