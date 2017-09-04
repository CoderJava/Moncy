package com.ysn.moncy.view.submenu.convert.choosecurrency

import android.content.Context
import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.submenu.convert.choosecurrency.adapter.AdapterChooseConvertCurrency

/**
 * Created by root on 31/08/17.
 */

class ChooseConvertCurrencyPresenter : MvpPresenter<ChooseConvertCurrencyView> {

    private val TAG = javaClass.simpleName
    private var chooseConvertCurrencyView: ChooseConvertCurrencyView? = null

    /**
     * On attach view
     * @param mvpView
     * On attach view choose convert currency view
     */
    override fun onAttach(mvpView: ChooseConvertCurrencyView) {
        chooseConvertCurrencyView = mvpView
    }

    /**
     * On detach view
     */
    override fun onDetach() {
        chooseConvertCurrencyView = null
    }

    /**
     * On load data to setup adapter choose convert currency
     * @param context
     * Context
     * @param listMergeConvertCurrency
     * List data merge convert currency
     */
    fun onLoadData(context: Context, listMergeConvertCurrency: List<MergeConvertCurrency>) {
        val adapterChooseConvertCurrency = AdapterChooseConvertCurrency(
                context = context,
                listMergeConvertCurrency = listMergeConvertCurrency,
                listenerViewHolderChooseConvertCurrency = object : AdapterChooseConvertCurrency.ListenerViewHolderChooseConvertCurrency {
                    override fun onClick(mergeConvertCurrency: MergeConvertCurrency) {
                        chooseConvertCurrencyView?.onClickItem(mergeConvertCurrency)
                    }
                }
        )
        chooseConvertCurrencyView?.loadData(adapterChooseConvertCurrency)
    }

}
