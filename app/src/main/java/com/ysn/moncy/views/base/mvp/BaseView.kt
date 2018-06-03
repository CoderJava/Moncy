package com.ysn.moncy.views.base.mvp

interface BaseView {

    fun onError()

    fun setPresenter(presenter: BasePresenter<*>)

}