package com.ysn.moncy.view.submenu.about

import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 02/09/17.
 */
interface AboutView : MvpView {

    /**
     * Method to load data success
     * @param versionName
     * Version name app
     */
    fun loadData(versionName: String?)

    /**
     * Method to load data failed
     */
    fun loadDataFailed()
}