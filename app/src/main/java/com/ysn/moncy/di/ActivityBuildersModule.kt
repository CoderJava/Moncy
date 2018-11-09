package com.ysn.moncy.di

import com.ysn.moncy.views.ui.activity.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    /*@ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity*/

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}