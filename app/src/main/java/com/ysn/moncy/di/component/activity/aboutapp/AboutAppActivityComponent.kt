package com.ysn.moncy.di.component.activity.aboutapp

import com.ysn.moncy.di.ActivityScope
import com.ysn.moncy.di.component.AppComponent
import com.ysn.moncy.di.module.activity.aboutapp.AboutAppActivityModule
import com.ysn.moncy.views.ui.activity.aboutapp.AboutAppActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(AboutAppActivityModule::class)])
interface AboutAppActivityComponent {

    fun inject(aboutAppActivity: AboutAppActivity)

}