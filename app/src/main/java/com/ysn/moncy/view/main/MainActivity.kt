package com.ysn.moncy.view.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.ysn.moncy.R
import com.ysn.moncy.view.submenu.about.AboutActivity
import com.ysn.moncy.view.submenu.available.AvailableCurrencyActivity
import com.ysn.moncy.view.submenu.convert.ConvertCurrencyActivity
import com.ysn.moncy.view.submenu.historical.date.HistoricalDateActivity
import com.ysn.moncy.view.submenu.live.CurrencyNowActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView, View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var mainPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        initListener()
        initToolbar()
        onAttachView()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_activity_main)
    }

    private fun initPresenter() {
        mainPresenter = MainPresenter()
    }

    override fun onAttachView() {
        mainPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        mainPresenter?.onDetach()
    }

    private fun initListener() {
        relative_layout_item_menu_currency_now_activity_main.setOnClickListener(this)
        relative_layout_item_menu_list_currency_activity_main.setOnClickListener(this)
        relative_layout_item_menu_historical_currency_activity_main.setOnClickListener(this)
        relative_layout_item_menu_convert_currency_activity_main.setOnClickListener(this)
        relative_layout_item_menu_data_offline_activity_main.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.menu_item_about -> {
            startActivity(Intent(this, AboutActivity::class.java))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.relative_layout_item_menu_currency_now_activity_main -> {
                startActivity(Intent(this, CurrencyNowActivity::class.java))
            }
            R.id.relative_layout_item_menu_list_currency_activity_main -> {
                startActivity(Intent(this, AvailableCurrencyActivity::class.java))
            }
            R.id.relative_layout_item_menu_historical_currency_activity_main -> {
                startActivity(Intent(this, HistoricalDateActivity::class.java))
            }
            R.id.relative_layout_item_menu_convert_currency_activity_main -> {
                startActivity(Intent(this, ConvertCurrencyActivity::class.java))
            }
            R.id.relative_layout_item_menu_data_offline_activity_main -> {
                // todo: start intent to DataOffline (coming soon)
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

}

