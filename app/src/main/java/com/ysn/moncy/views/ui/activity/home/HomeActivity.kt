package com.ysn.moncy.views.ui.activity.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ysn.moncy.R
import com.ysn.moncy.di.Injectable
import com.ysn.moncy.views.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

}
