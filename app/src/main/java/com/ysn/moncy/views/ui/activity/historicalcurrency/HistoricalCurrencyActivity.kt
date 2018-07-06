package com.ysn.moncy.views.ui.activity.historicalcurrency

import android.os.Bundle
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.historicalcurrency.DaggerHistoricalCurrencyActivityComponent
import com.ysn.moncy.di.module.activity.historicalcurrency.HistoricalCurrencyActivityModule
import com.ysn.moncy.views.base.BaseActivity
import javax.inject.Inject

class HistoricalCurrencyActivity : BaseActivity(), HistoricalCurrencyView {

    @Inject
    lateinit var presenter: HistoricalCurrencyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_currency)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerHistoricalCurrencyActivityComponent.builder()
                .appComponent(getAppComponent())
                .historicalCurrencyActivityModule(HistoricalCurrencyActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

}
