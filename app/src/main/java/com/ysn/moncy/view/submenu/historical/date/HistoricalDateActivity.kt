package com.ysn.moncy.view.submenu.historical.date

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.ysn.moncy.R
import com.ysn.moncy.model.merge.live.MergeHistorical
import com.ysn.moncy.view.submenu.historical.result.HistoricalResultFragment
import kotlinx.android.synthetic.main.activity_historical_date.*
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat
import java.util.*

class HistoricalDateActivity : AppCompatActivity(), HistoricalDateView, View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var historicalDatePresenter: HistoricalDatePresenter? = null
    private lateinit var progressDialog: ProgressDialog

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
                initProgressDialog()
                val dateHistory = text_view_value_date_activity_historical_date
                        .text
                        .toString()
                        .let {
                            it.substring(6, it.length) + "-" +
                                    it.substring(3, 5) + "-" +
                                    it.substring(0, 2)
                        }
                historicalDatePresenter?.onSubmit(this, dateHistory)
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.please_wait))
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    override fun submit(listMergeHistorical: ArrayList<MergeHistorical>) {
        supportFragmentManager
                .beginTransaction()
                ?.setCustomAnimations(
                        R.anim.slide_in_from_bottom_to_top,
                        R.anim.slide_out_from_bottom_to_top,
                        R.anim.slide_in_from_top_to_bottom,
                        R.anim.slide_out_from_top_to_bottom
                )
                ?.add(
                        R.id.frame_layout_container_activity_historical_date,
                        HistoricalResultFragment(),
                        HistoricalResultFragment::class.java.simpleName
                )
                ?.addToBackStack(null)
                ?.commit()
        progressDialog.dismiss()
        EventBus.getDefault().postSticky(listMergeHistorical)

    }

    override fun submitFailed() {
        progressDialog.dismiss()
        Snackbar.make(
                findViewById(android.R.id.content),
                getString(R.string.load_data_failed),
                Snackbar.LENGTH_LONG
        ).show()
    }
}
