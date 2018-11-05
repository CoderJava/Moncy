package com.ysn.moncy.views.ui.activity.country

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.country.DaggerCountryActivityComponent
import com.ysn.moncy.di.module.activity.country.CountryActivityModule
import com.ysn.moncy.utils.ItemDecorationMargin
import com.ysn.moncy.utils.hideLoading
import com.ysn.moncy.utils.showLoading
import com.ysn.moncy.utils.showSnackbarMessage
import com.ysn.moncy.views.base.BaseActivity
import com.ysn.moncy.views.ui.activity.country.adapter.AdapterCountry
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.toolbar_main_layout.view.*
import javax.inject.Inject

class CountryActivity : BaseActivity(), CountryView {

    @Inject
    lateinit var presenter: CountryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        initToolbar()
        doLoadData()
    }

    private fun doLoadData() {
        showLoading(viewLoading = loading_activity_country, viewContent = constraint_layout_content_activity_country)
        presenter.onLoadData()
    }

    override fun getViewContext(): Context = this

    override fun loadData(adapterCountry: AdapterCountry) {
        hideLoading(viewLoading = loading_activity_country, viewContent = constraint_layout_content_activity_country)
        recycler_view_country_activity_country.layoutManager = LinearLayoutManager(this)
        recycler_view_country_activity_country.addItemDecoration(ItemDecorationMargin(this))
        recycler_view_country_activity_country.adapter = adapterCountry
    }

    override fun loadDataFailed(message: String?) {
        hideLoading(viewLoading = loading_activity_country, viewContent = constraint_layout_content_activity_country)
        showSnackbarMessage(message = message!!)
    }

    private fun initToolbar() {
        toolbar_activity_country.text_view_title_toolbar_main_layout.text = getString(R.string.country)
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
