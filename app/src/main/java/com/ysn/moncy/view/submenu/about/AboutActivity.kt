package com.ysn.moncy.view.submenu.about

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar

import com.ysn.moncy.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity(), AboutView {

    private val TAG = javaClass.simpleName
    private var aboutPresenter: AboutPresenter? = null

    /**
     * @param savedInstanceState
     * Bundle savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        initPresenter()
        onAttachView()
        doLoadData()
    }

    /**
     * Initialize presenter
     */
    private fun initPresenter() {
        aboutPresenter = AboutPresenter()
    }

    /**
     * Do load data
     */
    private fun doLoadData() {
        aboutPresenter?.onLoadData(this)
    }

    /**
     * @param versionName
     * Load data success with passing value version name app
     */
    override fun loadData(versionName: String?) {
        text_view_app_version_activity_about.text = versionName
    }

    /**
     * Load data failed
     */
    override fun loadDataFailed() {
        Snackbar.make(
                findViewById(android.R.id.content),
                getString(R.string.internal_error),
                Snackbar.LENGTH_SHORT
        ).show()
    }

    /**
     * On attach view
     */
    override fun onAttachView() {
        aboutPresenter?.onAttach(this)
    }

    /**
     * On detach view
     */
    override fun onDetachView() {
        aboutPresenter?.onDetach()
    }

    /**
     * On resume
     */
    override fun onResume() {
        onAttachView()
        super.onResume()
    }

    /**
     * On destroy
     */
    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }
}
