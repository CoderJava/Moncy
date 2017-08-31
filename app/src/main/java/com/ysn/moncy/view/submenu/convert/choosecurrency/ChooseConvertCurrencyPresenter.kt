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

    override fun onAttach(mvpView: MvpView) {
        chooseConvertCurrencyView = mvpView as ChooseConvertCurrencyView
    }

    override fun onDetach() {
        chooseConvertCurrencyView = null
    }

    fun onLoadData(context: Context, listMergeConvertCurrency: List<MergeConvertCurrency>) {
        val adapterChooseConvertCurrency = AdapterChooseConvertCurrency(
                context = context,
                listMergeConvertCurrency = listMergeConvertCurrency
        )
        chooseConvertCurrencyView?.loadData(adapterChooseConvertCurrency)
    }

}
