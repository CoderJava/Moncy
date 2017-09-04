package com.ysn.moncy.view.submenu.convert.choosecurrency

import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.convert.choosecurrency.adapter.AdapterChooseConvertCurrency

/**
 * Created by root on 31/08/17.
 */
interface ChooseConvertCurrencyView : MvpView {

    /**
     * Method interface load data to setup adapter choose convert currency
     * @param adapterChooseConvertCurrency
     * Adapter choose convert currency
     */
    fun loadData(adapterChooseConvertCurrency: AdapterChooseConvertCurrency)

    /**
     * Method interface on click item from adapter choose convert currency
     */
    fun onClickItem(mergeConvertCurrency: MergeConvertCurrency)

}