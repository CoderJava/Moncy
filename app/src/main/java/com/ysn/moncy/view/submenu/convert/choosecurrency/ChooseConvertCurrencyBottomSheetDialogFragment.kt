package com.ysn.moncy.view.submenu.convert.choosecurrency


import android.app.Dialog
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ysn.moncy.R
import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import com.ysn.moncy.view.submenu.convert.choosecurrency.adapter.AdapterChooseConvertCurrency
import kotlinx.android.synthetic.main.bottom_sheet_dialog_fragment_choose_convert_currency.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * A simple [Fragment] subclass.
 */
class ChooseConvertCurrencyBottomSheetDialogFragment : BottomSheetDialogFragment(),
        ChooseConvertCurrencyView {

    private val TAG = javaClass.simpleName
    private var chooseConvertCurrencyPresenter: ChooseConvertCurrencyPresenter? = null
    private lateinit var listMergeConvertCurrency: List<MergeConvertCurrency>
    private lateinit var dialogView: View
    private lateinit var labelView: String

    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)
        dialogView = View.inflate(
                context,
                R.layout.bottom_sheet_dialog_fragment_choose_convert_currency,
                null
        )
        initPresenter()
        onAttachView()
        EventBus.getDefault().register(this)
        labelView = tag
        dialogView.toolbar_bottom_sheet_dialog_fragment_choose_convert_currency.title = "Choose Currency"
        dialog?.setContentView(dialogView)

        val parent = dialogView.parent as View
        val layoutParams = parent.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior as CoordinatorLayout.Behavior
        (behavior as BottomSheetBehavior).isHideable = true
        behavior.setBottomSheetCallback(
                object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            dismiss()
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        /** nothing to do in here */
                    }
                }
        )
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
    fun onMessageEvent(listMergeConvertCurrency: List<MergeConvertCurrency>) {
        this.listMergeConvertCurrency = listMergeConvertCurrency
        doLoadData()
    }

    override fun loadData(adapterChooseConvertCurrency: AdapterChooseConvertCurrency) {
        dialogView.recycler_view_data_bottom_sheet_dialog_fragment_choose_convert_currency.layoutManager =
                LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        dialogView.recycler_view_data_bottom_sheet_dialog_fragment_choose_convert_currency
                .addItemDecoration(dividerItemDecoration)
        dialogView.recycler_view_data_bottom_sheet_dialog_fragment_choose_convert_currency.adapter =
                adapterChooseConvertCurrency
    }

    override fun onClickItem(mergeConvertCurrency: MergeConvertCurrency) {
        val mapData = HashMap<String, Any>()
        mapData.put("fromView", labelView)
        mapData.put("data", mergeConvertCurrency)
        EventBus.getDefault().post(mapData)
        dismiss()
    }
}
