package com.ysn.moncy.views.ui.activity.home

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.home.DaggerHomeActivityComponent
import com.ysn.moncy.di.module.activity.home.HomeActivityModule
import com.ysn.moncy.views.base.BaseActivity
import com.ysn.moncy.views.ui.activity.aboutapp.AboutAppActivity
import com.ysn.moncy.views.ui.activity.country.CountryActivity
import com.ysn.moncy.views.ui.activity.currencynow.CurrencyNowActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView, View.OnClickListener {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initToolbar()
        initListeners()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerHomeActivityComponent.builder()
                .appComponent(getAppComponent())
                .homeActivityModule(HomeActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.menu_item_about_app_menu_home_activity -> {
                    val intentAboutAppActivity = intentFor<AboutAppActivity>()
                    startActivity(intentAboutAppActivity)
                    true
                }
                else -> {
                    super.onOptionsItemSelected(item)
                }
            }

    private fun initToolbar() {
        val toolbar = find<Toolbar>(R.id.toolbar_activity_home)
        setSupportActionBar(toolbar)
    }

    private fun initListeners() {
        relative_layout_container_currency_now_activity_home.setOnClickListener(this)
        relative_layout_container_country_currency_activity_home.setOnClickListener(this)
        relative_layout_container_history_currency_activity_home.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                R.id.relative_layout_container_currency_now_activity_home -> {
                    val intentCurrencyNowActivity = intentFor<CurrencyNowActivity>()
                    startActivity(intentCurrencyNowActivity)
                }
                R.id.relative_layout_container_country_currency_activity_home -> {
                    val intentCountryActivity = intentFor<CountryActivity>()
                    startActivity(intentCountryActivity)
                }
                R.id.relative_layout_container_history_currency_activity_home -> {
                    toast("This feature coming soon")
                }
                else -> {
                    /* nothing to do in here */
                }
            }
        }
    }

}
