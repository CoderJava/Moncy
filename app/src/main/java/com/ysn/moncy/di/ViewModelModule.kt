package com.ysn.moncy.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ysn.moncy.viewmodel.ViewModelFactory
import com.ysn.moncy.views.ui.activity.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}