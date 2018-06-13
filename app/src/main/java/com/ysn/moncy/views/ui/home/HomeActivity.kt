package com.ysn.moncy.views.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.home.DaggerHomeActivityComponent
import com.ysn.moncy.di.module.activity.home.HomeActivityModule
import com.ysn.moncy.views.base.BaseActivity
import com.ysn.moncy.views.ui.aboutapp.AboutAppActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initToolbar()
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
        setSupportActionBar(toolbar_activity_home)
    }
}
