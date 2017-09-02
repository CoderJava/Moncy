package com.ysn.moncy.view.submenu.about

import android.content.Context
import android.content.pm.PackageManager
import com.ysn.moncy.view.base.mvp.MvpPresenter

/**
 * Created by root on 02/09/17.
 */
class AboutPresenter : MvpPresenter<AboutView> {

    private val TAG = javaClass.simpleName
    private var aboutView: AboutView? = null

    /**
     * On attach view
     * @param mvpView
     * Attach view AboutView
     */
    override fun onAttach(mvpView: AboutView) {
        aboutView = mvpView
    }

    /**
     * On detach view
     */
    override fun onDetach() {
        aboutView = null
    }

    /**
     * On load data when view is shown
     * @param context
     * Context
     */
    fun onLoadData(context: Context) {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            val versionName = packageInfo.versionName
            aboutView?.loadData(versionName)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            aboutView?.loadDataFailed()
        }
    }

}