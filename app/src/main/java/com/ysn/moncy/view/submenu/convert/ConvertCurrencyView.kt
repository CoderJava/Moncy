package com.ysn.moncy.view.submenu.convert

import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 26/08/17.
 */
interface ConvertCurrencyView : MvpView {

    /**
     * Method interface load data currency success to get currency code and flag country
     * @param listMergeConvertCurrency
     * List data merge convert currency
     */
    fun loadData(listMergeConvertCurrency: List<MergeConvertCurrency>)

    /**
     * Method interface load data currency failed to get currency code and flag country
     */
    fun loadDataFailed()

    /**
     * Method interface load data converter currency success
     */
    fun loadDataConverterCurrency(valueConverterCurrency: Double)

    /**
     * Method interface load data converter currency failed
     */
    fun loadDataConverterCurrencyFailed()

}