package com.ysn.moncy.view.submenu.live

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.ysn.moncy.R

class CurrencyNowActivity : AppCompatActivity(), CurrencyNowView {

    private val TAG = javaClass.simpleName
    private var currencyNowPresenter: CurrencyNowPresenter? = null
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_now)
        initPresenter()
        onAttachView()
        doLoadData()
    }

    private fun doLoadData() {
        initProgressDialog()
        currencyNowPresenter?.onLoadDataCurrency(this)
    }

    private fun initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }
        progressDialog.setMessage(getString(R.string.please_wait))
        progressDialog.setCancelable(false)
        progressDialog.show()
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

    override fun loadDataCurrency() {
        currencyNowPresenter?.onLoadDataCountry()
    }

    override fun loadDataCurrencyFailed() {
        progressDialog.dismiss()
        val builder = AlertDialog.Builder(this)
                .setMessage("Load data failed")
        builder.create().show()
    }
}
