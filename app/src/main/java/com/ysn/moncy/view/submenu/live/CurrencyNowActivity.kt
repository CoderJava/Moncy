package com.ysn.moncy.view.submenu.live

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ysn.moncy.R

class CurrencyNowActivity : AppCompatActivity(), CurrencyNowView {

    private val TAG = javaClass.simpleName
    private var currencyNowPresenter: CurrencyNowPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_now)
        initPresenter()
        onAttachView()
        doLoadData()
    }

    private fun doLoadData() {
        currencyNowPresenter?.onLoadData()
    }

    private fun initPresenter() {
        currencyNowPresenter = CurrencyNowPresenter()
    }

    override fun onAttachView() {
        currencyNowPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        currencyNowPresenter?.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }
}
