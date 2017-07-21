package com.ysn.moncy.view.main

import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView

/**
 * Created by root on 21/07/17.
 */
class MainPresenter : MvpPresenter<MainView> {

    private val TAG = javaClass.simpleName
    private var mainView: MainView? = null

    override fun onAttach(mvpView: MvpView) {
        mainView = mvpView as MainView
    }

    override fun onDetach() {
        mainView = null
    }

}