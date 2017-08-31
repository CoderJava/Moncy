package com.ysn.moncy.view.submenu.convert.choosecurrency


import android.app.Dialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

import com.ysn.moncy.R
import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import com.ysn.moncy.view.submenu.convert.choosecurrency.adapter.AdapterChooseConvertCurrency
import kotlinx.android.synthetic.main.bottom_sheet_dialog_fragment_choose_convert_currency.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ChooseConvertCurrency : BottomSheetDialogFragment(),
        ChooseConvertCurrencyView {

    private val TAG = javaClass.simpleName
    private var chooseConvertCurrencyPresenter: ChooseConvertCurrencyPresenter? = null
    private lateinit var listMergeConvertCurrency: List<MergeConvertCurrency>
    private var recyclerView: RecyclerView? = null

    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)
        val view = View.inflate(
                context,
                R.layout.bottom_sheet_dialog_fragment_choose_convert_currency,
                null
        )
        initPresenter()
        onAttachView()
        recyclerView = view.findViewById(R.id.recycler_view_data_bottom_sheet_dialog_fragment_choose_convert_currency) as RecyclerView?
        EventBus.getDefault().register(this)
        dialog?.setContentView(view)
    }

    private fun initPresenter() {
        chooseConvertCurrencyPresenter = ChooseConvertCurrencyPresenter()
    }

    override fun onAttachView() {
        chooseConvertCurrencyPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        chooseConvertCurrencyPresenter?.onDetach()
    }

    private fun doLoadData() {
        chooseConvertCurrencyPresenter?.onLoadData(context, listMergeConvertCurrency)
    }

    @Subscribe(sticky = true)
    public fun onMessageEvent(listMergeConvertCurrency: List<MergeConvertCurrency>) {
        this.listMergeConvertCurrency = listMergeConvertCurrency
        Log.d(TAG, "listMergeConvertCurrency: " + listMergeConvertCurrency)
        doLoadData()
    }

    override fun loadData(adapterChooseConvertCurrency: AdapterChooseConvertCurrency) {
        Log.d(TAG, "context: " + context);
        Log.d(TAG, "adapterChooseConvertCurrency: " + adapterChooseConvertCurrency)
        Log.d(TAG, "recycler_view_data_bottom_sheet_dialog_fragment_choose_onvert_currency: " + recycler_view_data_bottom_sheet_dialog_fragment_choose_convert_currency)
        Log.d(TAG, "recyclerView: " + recyclerView)
        recycler_view_data_bottom_sheet_dialog_fragment_choose_convert_currency.layoutManager =
                LinearLayoutManager(context)
        recycler_view_data_bottom_sheet_dialog_fragment_choose_convert_currency.adapter = adapterChooseConvertCurrency
    }
}
