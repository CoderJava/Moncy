package com.ysn.moncy.views.ui.splashscreen

import android.os.Bundle
import com.ysn.moncy.R
import com.ysn.moncy.di.component.activity.splashscreen.DaggerSplashScreenActivityComponent
import com.ysn.moncy.di.module.activity.splashscreen.SplashScreenActivityModule
import com.ysn.moncy.utils.showToastMessage
import com.ysn.moncy.views.base.BaseActivity
import com.ysn.moncy.views.ui.home.HomeActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashScreenActivity : BaseActivity(), SplashScreenView {

    @Inject
    lateinit var presenter: SplashScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        doLoadData()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerSplashScreenActivityComponent.builder()
                .appComponent(getAppComponent())
                .splashScreenActivityModule(SplashScreenActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    private fun doLoadData() {
        Observable.just(true)
                .delay(1000 * 3, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                            val intentHomeActivity = intentFor<HomeActivity>().clearTask().newTask()
                            startActivity(intentHomeActivity)
                        },
                        {
                            it.printStackTrace()
                            showToastMessage(message = it.message.toString())
                        },
                        {
                            /* nothing to do in here */
                        }
                )
    }

}
