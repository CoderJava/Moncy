package com.ysn.moncy.view.submenu.available

import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ysn.moncy.R
import com.ysn.moncy.view.submenu.available.adapter.AdapterAvailableCurrency
import kotlinx.android.synthetic.main.activity_available_currency.*

class AvailableCurrencyActivity : AppCompatActivity(), AvailableCurrencyView, View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var availableCurrencyPresenter: AvailableCurrencyPresenter? = null
    private lateinit var progressDialog: ProgressDialog

    /**
     * @param savedInstanceState
     * Bundle savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_currency)
        initPresenter()
        onAttachView()
        initToolbar()
        initListener()
        doLoadData()
    }

    /**
     * On options item selected
     * @param item
     * Item selected
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    /**
     * On destroy activity
     */
    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
    }

    /**
     * Do load data available currency
     */
    private fun doLoadData() {
        initProgressDialog()
        recycler_view_data_available_currency.visibility = View.GONE
        relative_layout_container_refresh_activity_available_currency.visibility = View.GONE
        availableCurrencyPresenter?.onLoadData(this)
    }

    /**
     * Initialize Progress Dialog
     */
    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.please_wait))
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    /**
     *
     */
    private fun initListener() {
        button_try_again_activity_available_currency.setOnClickListener(this)

        swipe_refresh_layout_activity_available_currency.setOnRefreshListener {
            doRefreshData()
        }
    }

    /**
     * Do refresh data available currency
     */
    private fun doRefreshData() {
        swipe_refresh_layout_activity_available_currency.isRefreshing = true
        recycler_view_data_available_currency?.visibility = View.GONE
        relative_layout_container_refresh_activity_available_currency?.visibility = View.GONE
        availableCurrencyPresenter?.onLoadData(this, true)
    }

    /**
     * Method implements View.OnClickListener
     * @param view
     * View UI when clicked
     */
    override fun onClick(view: View?) {
        when (view?.id) {
            button_try_again_activity_available_currency.id -> {
                doRefreshData()
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

    /**
     * Initialize toolbar
     */
    private fun initToolbar() {
        toolbar_activity_available_currency.title = "Available Currency"
        setSupportActionBar(toolbar_activity_available_currency)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * Initialize presenter
     */
    private fun initPresenter() {
        availableCurrencyPresenter = AvailableCurrencyPresenter()
    }

    /**
     * On attach view
     */
    override fun onAttachView() {
        availableCurrencyPresenter?.onAttach(this)
    }

    /**
     * On detach view
     */
    override fun onDetachView() {
        availableCurrencyPresenter?.onDetach()
    }

    /**
     * Load data available currency failed
     * @param isRefresh
     * Indicator that method load data is call for refresh or not
     */
    override fun loadDataFailed(isRefresh: Boolean) {
        setProgressViewDone(isRefresh)

        recycler_view_data_available_currency.visibility = View.GONE
        relative_layout_container_refresh_activity_available_currency.visibility = View.VISIBLE
        showSnackbarFailed()
    }

    /**
     * Show message failed in snack bar
     */
    private fun showSnackbarFailed() {
        Snackbar.make(
                findViewById(android.R.id.content),
                R.string.load_data_failed,
                Snackbar.LENGTH_LONG
        ).show()
    }

    /**
     * Load data available currency success
     * @param adapterAvailableCurrency
     * Adapter available currency for recycler view
     * @param isRefresh
     * Indicator that load data is used for refresh or not
     */
    override fun loadData(adapterAvailableCurrency: AdapterAvailableCurrency, isRefresh: Boolean) {
        setProgressViewDone(isRefresh)

        recycler_view_data_available_currency.visibility = View.VISIBLE
        relative_layout_container_refresh_activity_available_currency.visibility = View.GONE

        recycler_view_data_available_currency.layoutManager = LinearLayoutManager(this)
        recycler_view_data_available_currency.adapter = adapterAvailableCurrency
    }

    /**
     * Set progress dialog to dismiss and set swipe is refreshing to false
     * @param isRefresh
     * Indicator that progress view is done or not
     */
    private fun setProgressViewDone(isRefresh: Boolean) {
        if (!isRefresh) {
            progressDialog.dismiss()
        } else {
            swipe_refresh_layout_activity_available_currency.isRefreshing = false
        }
    }
}
