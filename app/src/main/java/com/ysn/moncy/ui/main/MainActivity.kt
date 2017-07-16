package com.ysn.moncy.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.ysn.moncy.R
import com.ysn.moncy.ui.trend.CurrencyNowActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        relative_layout_item_menu_currency_now_activity_main.setOnClickListener(this)
        relative_layout_item_menu_list_currency_activity_main.setOnClickListener(this)
        relative_layout_item_menu_historical_currency_activity_main.setOnClickListener(this)
        relative_layout_item_menu_convert_currency_activity_main.setOnClickListener(this)
        relative_layout_item_menu_data_offline_activity_main.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.relative_layout_item_menu_currency_now_activity_main -> {
                // todo: start intent to CurrencyNow
                startActivity(Intent(this, CurrencyNowActivity::class.java))
            }
            R.id.relative_layout_item_menu_list_currency_activity_main -> {
                // todo: start intent to ListCurrency
            }
            R.id.relative_layout_item_menu_historical_currency_activity_main -> {
                // todo: start intent to HistoricalCurrency
            }
            R.id.relative_layout_item_menu_convert_currency_activity_main -> {
                // todo: start intent to ConvertCurrency
            }
            R.id.relative_layout_item_menu_data_offline_activity_main -> {
                // todo: start intent to DataOffline
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

}
