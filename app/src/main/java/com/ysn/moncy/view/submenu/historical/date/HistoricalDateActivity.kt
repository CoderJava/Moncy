package com.ysn.moncy.view.submenu.historical.date

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View

import com.ysn.moncy.R
import kotlinx.android.synthetic.main.activity_historical_date.*
import java.text.SimpleDateFormat
import java.util.*

class HistoricalDateActivity : AppCompatActivity(), HistoricalDateView, View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var historicalDatePresenter: HistoricalDatePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_date)
        initPresenter()
        onAttachView()
        initToolbar()
        initListener()
        doLoadData()
    }

    private fun doLoadData() {
        val today = SimpleDateFormat("dd-MM-yyyy", Locale.US).format(Date())
        text_view_value_date_activity_historical_date.text = today
    }

    private fun initListener() {
        button_change_date_activity_historical_date.setOnClickListener(this)
        button_submit_activity_historical_date.setOnClickListener(this)
    }

    private fun initToolbar() {
        toolbar_activity_historical_date.title = "History Currency"
        setSupportActionBar(toolbar_activity_historical_date)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
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

    fun initPresenter() {
        historicalDatePresenter = HistoricalDatePresenter()
    }

    override fun onAttachView() {
        historicalDatePresenter?.onAttach(this)
    }

    override fun onDetachView() {
        historicalDatePresenter?.onDetach()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            button_change_date_activity_historical_date.id -> {
                val calendar = Calendar.getInstance()
                val strDateTemp = text_view_value_date_activity_historical_date.text.toString()
                val dateTemp = SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(strDateTemp)
                calendar.time = dateTemp
                DatePickerDialog(
                        this,
                        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                            calendar.set(Calendar.YEAR, year)
                            calendar.set(Calendar.MONTH, month)
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                            val dateSelected = SimpleDateFormat("dd-MM-yyyy", Locale.US).format(calendar.time)
                            text_view_value_date_activity_historical_date.text = dateSelected
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            button_submit_activity_historical_date.id -> {
                // todo: do something
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }
}
