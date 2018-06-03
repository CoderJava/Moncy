package com.ysn.moncy.di.module

import com.ysn.moncy.api.fixer.FixerEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class FixerApiModule {

    @Provides
    @Singleton
    fun provideEndpoints(@Named("fixer") retrofit: Retrofit): FixerEndpoints = retrofit.create(FixerEndpoints::class.java)

}