package com.ysn.moncy.views.ui.activity.currencynow

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.currencynow.DaggerCurrencyNowActivityComponent
import com.ysn.moncy.di.module.activity.currencynow.CurrencyNowActivityModule
import com.ysn.moncy.utils.hideLoading
import com.ysn.moncy.utils.showLoading
import com.ysn.moncy.utils.showSnackbarMessage
import com.ysn.moncy.views.base.BaseActivity
import com.ysn.moncy.views.ui.activity.currencynow.adapter.AdapterCurrencyNow
import com.ysn.moncy.views.ui.activity.currencynow.adapter.CurrencyNowItemDecoration
import kotlinx.android.synthetic.main.activity_currency_now.*
import javax.inject.Inject

class CurrencyNowActivity : BaseActivity(), CurrencyNowView {

    @Inject
    lateinit var presenter: CurrencyNowPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_now)
        doLoadData()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerCurrencyNowActivityComponent.builder()
                .appComponent(getAppComponent())
                .currencyNowActivityModule(CurrencyNowActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    private fun doLoadData() {
        showLoading(viewLoading = loading_activity_currency_now, viewContent = constraint_layout_content_activity_currency_now)
        presenter.onLoadData()
    }

    override fun loadData(adapterCurrencyNow: AdapterCurrencyNow, datetimeFormatting: String, source: String) {
        hideLoading(viewLoading = loading_activity_currency_now, viewContent = constraint_layout_content_activity_currency_now)
        text_view_date_time_activity_currency_now.text = datetimeFormatting
        text_view_source_value_activity_currency_now.text = source
        recycler_view_rates_activity_currency_now.layoutManager = LinearLayoutManager(this)
        recycler_view_rates_activity_currency_now.addItemDecoration(CurrencyNowItemDecoration(this))
        recycler_view_rates_activity_currency_now.adapter = adapterCurrencyNow
    }

    override fun loadDataFailed(message: String?) {
        hideLoading(viewLoading = loading_activity_currency_now, viewContent = constraint_layout_content_activity_currency_now)
        showSnackbarMessage(message = message!!)
    }
}
