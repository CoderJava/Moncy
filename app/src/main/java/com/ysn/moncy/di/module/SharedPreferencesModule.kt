package com.ysn.moncy.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences = application.getSharedPreferences("PREF_APP", Context.MODE_PRIVATE)

}