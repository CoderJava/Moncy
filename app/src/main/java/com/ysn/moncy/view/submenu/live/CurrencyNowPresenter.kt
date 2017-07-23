package com.ysn.moncy.view.submenu.live

import android.content.Context
import android.util.Log
import com.ysn.moncy.BuildConfig
import com.ysn.moncy.model.live.CurrencyNow
import com.ysn.moncy.network.ApiCurrencyService
import com.ysn.moncy.network.NetworkClient
import com.ysn.moncy.view.base.mvp.MvpPresenter
import com.ysn.moncy.view.base.mvp.MvpView
import com.ysn.moncy.view.main.adapter.AdapterCurrencyNow
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by root on 21/07/17.
 */
class CurrencyNowPresenter : MvpPresenter<CurrencyNowView> {

    private val TAG = javaClass.simpleName
    private var currencyNowView: CurrencyNowView? = null
    private var adapterCurrencyNow: AdapterCurrencyNow? = null

    override fun onAttach(mvpView: MvpView) {
        currencyNowView = mvpView as CurrencyNowView
    }

    override fun onDetach() {
        currencyNowView = null
    }

    fun onLoadDataCurrency(context: Context) {
        var currencyNow: CurrencyNow? = null
        val apiCurrency = NetworkClient.RetrofitCurrency
                .getRetrofitCurrency()?.create(ApiCurrencyService::class.java)
        apiCurrency?.getLive(BuildConfig.API_KEY_CURRENCY)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : Observer<CurrencyNow> {
                    override fun onComplete() {
                        Log.d(TAG, "onComplete")
                        val listQuotesValues = ArrayList<String>()
                        val listQuotesLabels = ArrayList<String>()
                        val mapQuotes = currencyNow?.quotes
                        val keys = mapQuotes?.keys?.iterator()
                        val source = currencyNow?.source
                        while (keys?.hasNext()!!) {
                            val key = keys.next().let {
                                it.substring(source?.length as Int, it.length)
                            }
                            listQuotesLabels.add(key)
                            listQuotesValues.add(mapQuotes[source + key].toString())
                        }
                        currencyNowView?.loadDataCurrency()
                    }

                    override fun onNext(t: CurrencyNow) {
                        Log.d(TAG, "onNext currencyNow: " + t)
                        currencyNow = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        currencyNowView?.loadDataCurrencyFailed()
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "onSubscribe disposable: $d")
                    }
                })
    }

    fun onLoadDataCountry() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

