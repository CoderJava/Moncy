package com.ysn.moncy.views.ui.activity.currencynow

import android.os.Bundle
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.currencynow.DaggerCurrencyNowActivityComponent
import com.ysn.moncy.di.module.activity.currencynow.CurrencyNowActivityModule
import com.ysn.moncy.views.base.BaseActivity
import javax.inject.Inject

class CurrencyNowActivity : BaseActivity(), CurrencyNowView {

    @Inject
    lateinit var presenter: CurrencyNowPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_now)
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
}
