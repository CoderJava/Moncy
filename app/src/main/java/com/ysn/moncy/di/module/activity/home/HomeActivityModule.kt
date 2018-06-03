package com.ysn.moncy.di.module.activity.home

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.views.ui.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @ActivityScope
    @Provides
    internal fun provideHomePresenter(): HomePresenter = HomePresenter()

}