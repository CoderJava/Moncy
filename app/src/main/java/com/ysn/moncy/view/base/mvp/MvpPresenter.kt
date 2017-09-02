package com.ysn.moncy.view.base.mvp

/**
 * Created by root on 21/07/17.
 * Base MVP Presenter
 */
interface MvpPresenter<in T : MvpView> {

    /**
     * @param t
     * Attach view implements MvpView
     */
    fun onAttach(mvpView : T)

    /**
     * Detach view implements MvpView
     */
    fun onDetach()
}
