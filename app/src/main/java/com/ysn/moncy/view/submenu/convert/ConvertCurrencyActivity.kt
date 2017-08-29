package com.ysn.moncy.view.submenu.convert

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.MenuItem
import android.view.View

import com.ysn.moncy.R
import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import kotlinx.android.synthetic.main.activity_convert_currency.*

class ConvertCurrencyActivity : AppCompatActivity(), ConvertCurrencyView, View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var convertCurrencyPresenter: ConvertCurrencyPresenter? = null
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert_currency)
        initPresenter()
        onAttachView()
        initToolbar()
        initListener()
        doLoadData()
    }

    private fun initToolbar() {
        toolbar_activity_convert_currency.title = "Convert Currency"
        setSupportActionBar(toolbar_activity_convert_currency)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initListener() {
        text_view_value_source_code_currency_activity_convert_currency.setOnClickListener(this)
        image_view_icon_drop_down_value_source_currency_activity_convert_currency.setOnClickListener(this)
        text_view_value_to_code_currency_activity_convert_currency.setOnClickListener(this)
        image_view_icon_drop_down_value_to_currency_activity_convert_currency.setOnClickListener(this)
        button_try_again_activity_convert_currency.setOnClickListener(this)
        button_keypad_0_activity_convert_currency.setOnClickListener(this)
        button_keypad_1_activity_convert_currency.setOnClickListener(this)
        button_keypad_2_activity_convert_currency.setOnClickListener(this)
        button_keypad_3_activity_convert_currency.setOnClickListener(this)
        button_keypad_4_activity_convert_currency.setOnClickListener(this)
        button_keypad_5_activity_convert_currency.setOnClickListener(this)
        button_keypad_6_activity_convert_currency.setOnClickListener(this)
        button_keypad_7_activity_convert_currency.setOnClickListener(this)
        button_keypad_8_activity_convert_currency.setOnClickListener(this)
        button_keypad_9_activity_convert_currency.setOnClickListener(this)
        button_keypad_dot_activity_convert_currency.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_view_value_source_code_currency_activity_convert_currency, R.id.image_view_icon_drop_down_value_source_currency_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.text_view_value_to_code_currency_activity_convert_currency,
            R.id.image_view_icon_drop_down_value_to_currency_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_try_again_activity_convert_currency -> {
                doLoadData()
            }
            R.id.button_keypad_0_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_1_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_2_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_3_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_4_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_5_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_6_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_7_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_8_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_9_activity_convert_currency -> {
                // todo: do something in here
            }
            R.id.button_keypad_dot_activity_convert_currency -> {
                // todo: do something in here
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

    private fun doLoadData() {
        initProgressDialog()
        linear_layout_container_content_activity_convert_currency.visibility = View.GONE
        relative_layout_container_refresh_activity_convert_currency.visibility = View.GONE
        convertCurrencyPresenter?.onLoadData(this)
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.please_wait))
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    override fun onResume() {
        onAttachView()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
    }

    private fun initPresenter() {
        convertCurrencyPresenter = ConvertCurrencyPresenter()
    }

    override fun onAttachView() {
        convertCurrencyPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        convertCurrencyPresenter?.onDetach()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true;
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun loadData(listMergeConvertCurrency: List<MergeConvertCurrency>) {
        // todo: do something in here
        Log.d(TAG, "listMergeConvertCurrency: $listMergeConvertCurrency")
        setProgressViewDone()
        linear_layout_container_content_activity_convert_currency.visibility = View.VISIBLE
        relative_layout_container_refresh_activity_convert_currency.visibility = View.GONE
    }

    override fun loadDataFailed() {
        // todo: do something in here
        showSnackbarFailed()
        setProgressViewDone()
        linear_layout_container_content_activity_convert_currency.visibility = View.GONE
        relative_layout_container_refresh_activity_convert_currency.visibility = View.VISIBLE
    }

    private fun showSnackbarFailed() {
        Snackbar.make(
                findViewById(android.R.id.content),
                R.string.load_data_failed,
                Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setProgressViewDone() {
        progressDialog.dismiss()
    }
}
