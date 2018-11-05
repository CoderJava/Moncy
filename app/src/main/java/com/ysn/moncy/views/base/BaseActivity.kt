package com.ysn.moncy.views.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ysn.moncy.di.component.AppComponent
import com.ysn.moncy.views.base.mvp.BasePresenter
import com.ysn.moncy.views.base.mvp.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var presenter: BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
    }

    protected abstract fun onActivityInject()

    fun getAppComponent(): AppComponent = App.appComponent

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter = presenter
    }

    override fun onStart() {
        super.onStart()
        // TODO: do something in here if needed
    }

    override fun onStop() {
        super.onStop()
        // TODO: do something in here if needed
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
    }

}