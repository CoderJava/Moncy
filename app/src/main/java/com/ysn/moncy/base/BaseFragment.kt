package com.ysn.moncy.base

import android.support.v4.app.Fragment

/**
 * Created by root on 15/07/17.
 */

abstract class BaseFragment : Fragment(), Lifecycle.View {

    protected abstract val viewModel: Lifecycle.ViewModel

    override fun onResume() {
        super.onResume()
        viewModel.onViewResumed()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onViewAttached(this)
    }

    override fun onStop() {
        super.onStop()
        viewModel.onViewDetached()
    }
}
