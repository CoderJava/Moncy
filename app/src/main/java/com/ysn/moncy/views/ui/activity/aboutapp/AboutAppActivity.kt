package com.ysn.moncy.views.ui.activity.aboutapp

import android.os.Bundle
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.aboutapp.DaggerAboutAppActivityComponent
import com.ysn.moncy.di.module.activity.aboutapp.AboutAppActivityModule
import com.ysn.moncy.views.base.BaseActivity
import javax.inject.Inject

class AboutAppActivity : BaseActivity(), AboutAppView {

    @Inject
    lateinit var presenter: AboutAppPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerAboutAppActivityComponent.builder()
                .appComponent(getAppComponent())
                .aboutAppActivityModule(AboutAppActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

}
