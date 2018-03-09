package com.ysn.moncy.view.home

import com.ysn.moncy.view.base.mvp.MvpPresenter

/**
 * Created by root on 21/07/17.
 */
class HomePresenter : MvpPresenter<HomeView> {

    private val TAG = javaClass.simpleName
    private var homeView: HomeView? = null

    /**
     * On attach view
     * @param mvpView
     * Attach view HomeView
     */
    override fun onAttach(mvpView: HomeView) {
        homeView = mvpView
    }

    /**
     * On detach view
     */
    override fun onDetach() {
        homeView = null
    }

}