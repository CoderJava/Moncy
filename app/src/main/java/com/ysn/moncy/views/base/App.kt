package com.ysn.moncy.views.base

import android.app.Application
import com.ysn.moncy.di.component.AppComponent
import com.ysn.moncy.di.component.DaggerAppComponent
import com.ysn.moncy.di.module.AppModule

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}