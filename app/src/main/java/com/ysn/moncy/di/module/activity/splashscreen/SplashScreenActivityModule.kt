package com.ysn.moncy.di.module.activity.splashscreen

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.views.ui.activity.splashscreen.SplashScreenPresenter
import dagger.Module
import dagger.Provides

@Module
class SplashScreenActivityModule {

    @ActivityScope
    @Provides
    internal fun provideSplashScreenPresenter(): SplashScreenPresenter = SplashScreenPresenter()

}