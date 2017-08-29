package com.ysn.moncy.view.base.mvp

/**
 * Created by root on 21/07/17.
 */
interface MvpPresenter<T : MvpView> {

    fun onAttach(mvpView: MvpView)

    fun onDetach()
}
