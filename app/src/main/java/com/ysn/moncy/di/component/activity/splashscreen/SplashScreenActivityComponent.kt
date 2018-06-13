package com.ysn.moncy.di.component.activity.splashscreen

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.di.component.AppComponent
import com.ysn.moncy.di.module.activity.splashscreen.SplashScreenActivityModule
import com.ysn.moncy.views.ui.activity.splashscreen.SplashScreenActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(SplashScreenActivityModule::class)])
interface SplashScreenActivityComponent {

    fun inject(splashScreenActivity: SplashScreenActivity)

}