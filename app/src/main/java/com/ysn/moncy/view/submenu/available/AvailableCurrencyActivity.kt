package com.ysn.moncy.view.submenu.available

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ysn.moncy.R
import kotlinx.android.synthetic.main.activity_available_currency.*

class AvailableCurrencyActivity : AppCompatActivity(), AvailableCurrencyView {

    private val TAG = javaClass.simpleName
    private var availableCurrencyPresenter: AvailableCurrencyPresenter? = null
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_currency)
        initPresenter()
        onAttachView()
        initToolbar()
        initListener()
        doLoadData()
    }

    private fun doLoadData() {
        initProgressDialog()
        recycler_view_data_available_currency.visibility = View.GONE
        relative_layout_container_refresh_activity_available_currency.visibility = View.GONE
        availableCurrencyPresenter?.onLoadData(this)
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.please_wait))
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    private fun initListener() {
        swipe_refresh_layout_activity_available_currency.setOnRefreshListener {
            doRefreshData()
        }
    }

    private fun doRefreshData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initToolbar() {
        toolbar_activity_available_currency.title = "Available Currency"
        setSupportActionBar(toolbar_activity_available_currency)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initPresenter() {
        availableCurrencyPresenter = AvailableCurrencyPresenter()
    }

    override fun onAttachView() {
        availableCurrencyPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        availableCurrencyPresenter?.onDetach()
    }
}
