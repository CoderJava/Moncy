package com.ysn.moncy.views.ui.activity.country

import android.os.Bundle
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.country.DaggerCountryActivityComponent
import com.ysn.moncy.di.module.activity.country.CountryActivityModule
import com.ysn.moncy.views.base.BaseActivity
import javax.inject.Inject

class CountryActivity : BaseActivity(), CountryView {

    @Inject
    lateinit var presenter: CountryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerCountryActivityComponent.builder()
                .appComponent(getAppComponent())
                .countryActivityModule(CountryActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

}
