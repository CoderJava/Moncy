package com.ysn.moncy.di.module.activity.aboutapp

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.views.ui.activity.aboutapp.AboutAppPresenter
import dagger.Module
import dagger.Provides

@Module
class AboutAppActivityModule {

    @ActivityScope
    @Provides
    internal fun provideAboutAppPresenter(): AboutAppPresenter = AboutAppPresenter()

}