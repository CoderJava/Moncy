package com.ysn.moncy.view.submenu.live

import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.ysn.moncy.R
import com.ysn.moncy.R.id.relative_layout_container_activity_currency_now
import com.ysn.moncy.R.id.relative_layout_container_refresh_activity_currency_now
import com.ysn.moncy.model.currency.live.CurrencyNow
import com.ysn.moncy.view.submenu.live.adapter.AdapterCurrencyNow
import kotlinx.android.synthetic.main.activity_currency_now.*
import java.text.SimpleDateFormat
import java.util.*

class CurrencyNowActivity : AppCompatActivity(), CurrencyNowView, View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var currencyNowPresenter: CurrencyNowPresenter? = null
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_now)
        initPresenter()
        onAttachView()
        initToolbar()
        initListener()
        doLoadData()
    }

    private fun initListener() {
        button_try_again_activity_currency_now.setOnClickListener(this)

        swipe_refresh_layout_activity_currency_now.setOnRefreshListener {
            doRefreshData()
        }
    }

    private fun doRefreshData() {
        swipe_refresh_layout_activity_currency_now.isRefreshing = true
        relative_layout_container_activity_currency_now?.visibility = View.GONE
        relative_layout_container_refresh_activity_currency_now?.visibility = View.GONE
        currencyNowPresenter?.onLoadData(this, true)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            button_try_again_activity_currency_now.id -> {
                doRefreshData()
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

    private fun initToolbar() {
        toolbar_activity_currency_now.title = "Currency Now"
        setSupportActionBar(toolbar_activity_currency_now)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }


    private fun doLoadData() {
        initProgressDialog()
        relative_layout_container_activity_currency_now.visibility = View.GONE
        relative_layout_container_refresh_activity_currency_now.visibility = View.GONE
        currencyNowPresenter?.onLoadData(this)
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
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

    override fun loadData(adapterCurrencyNow: AdapterCurrencyNow, currencyNow: CurrencyNow?, isRefresh: Boolean) {
        setProgressViewDone(isRefresh)

        relative_layout_container_activity_currency_now.visibility = View.VISIBLE
        relative_layout_container_refresh_activity_currency_now.visibility = View.GONE

        text_view_value_source_activity_currency_now.text = currencyNow?.source
        text_view_timestamp_activity_currency_now.text = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US)
                .format(Date())

        recycler_view_data_activity_currency_now.layoutManager = LinearLayoutManager(this)
        recycler_view_data_activity_currency_now.adapter = adapterCurrencyNow
    }

    override fun loadDataFailed(isRefresh: Boolean) {
        setProgressViewDone(isRefresh)

        relative_layout_container_activity_currency_now.visibility = View.GONE
        relative_layout_container_refresh_activity_currency_now.visibility = View.VISIBLE
        showSnackbarFailed()
    }

    private fun setProgressViewDone(isRefresh: Boolean) {
        if (!isRefresh)
            progressDialog.dismiss()
        else
            swipe_refresh_layout_activity_currency_now.isRefreshing = false
    }

    private fun showSnackbarFailed() {
        Snackbar.make(
                findViewById(android.R.id.content),
                getString(R.string.load_data_failed),
                Snackbar.LENGTH_LONG
        ).show()
    }

}
