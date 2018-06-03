package com.ysn.moncy.di.module

import android.app.Application
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(val application: Application) {

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideResouce() = application.resources

}