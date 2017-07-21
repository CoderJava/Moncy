package com.ysn.moncy.network

import android.util.Log
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.HttpException

/**
 * Created by root on 21/07/17.
 */
abstract class NetworkCallback<M> : Subscriber<M> {

    private val TAG = javaClass.simpleName

    abstract fun onSuccess(model: M)

    abstract fun onFailure(message: String)

    abstract fun onFinish()

    override fun onError(t: Throwable?) {
        t?.printStackTrace()
        if (t is HttpException) {
            val code = t.code()
            val message = t.message()
            Log.d(TAG, "code: " + code)
            onFailure(message)
        } else {
            onFailure(t?.message!!)
        }
        onFinish()
    }

    override fun onComplete() {
        onFinish()
    }

    override fun onSubscribe(s: Subscription?) {
        // todo: I don't know what I'm doing in here
    }

    override fun onNext(t: M) {
        onSuccess(t)
    }
}