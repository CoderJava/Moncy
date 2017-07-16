package com.ysn.moncy.base

import android.support.annotation.CallSuper

import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableMaybeObserver

/**
 * Created by root on 16/07/17.
 */

abstract class NetworkViewModel {

    @Constants.RequestState private var requestState: Long
    lateinit var lastError: Throwable
    abstract val isRequestingInformation: Boolean

    init {
        this.requestState = Constants.REQUEST_NONE
    }

    @Constants.RequestState
    fun getRequestState(): Long {
        if (isRequestingInformation) {
            return Constants.REQUEST_RUNNING
        }
        return requestState
    }

    protected inner class MaybeNetworkObserver<T> : DisposableMaybeObserver<T>() {

        @CallSuper
        override fun onSuccess(@NonNull t: T) {
            requestState = Constants.REQUEST_SUCCEEDED
        }

        @CallSuper
        override fun onError(@NonNull e: Throwable) {
            lastError = e
            requestState = Constants.REQUEST_FAILED
        }

        override fun onComplete() {

        }
    }

}
