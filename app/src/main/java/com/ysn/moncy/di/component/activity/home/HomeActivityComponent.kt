package com.ysn.moncy.di.component.activity.home

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.di.component.AppComponent
import com.ysn.moncy.di.module.activity.home.HomeActivityModule
import com.ysn.moncy.views.ui.home.HomeActivity
import dagger.Component

@ActivityScope
@Component(dependencies = ([AppComponent::class]), modules = [(HomeActivityModule::class)])
interface HomeActivityComponent {

    fun inject(homeActivity: HomeActivity)

}