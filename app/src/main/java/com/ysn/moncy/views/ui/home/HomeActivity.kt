package com.ysn.moncy.views.ui.home

import android.os.Bundle
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.home.DaggerHomeActivityComponent
import com.ysn.moncy.di.module.activity.home.HomeActivityModule
import com.ysn.moncy.views.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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
}
