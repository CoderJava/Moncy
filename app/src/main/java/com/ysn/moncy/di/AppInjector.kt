package com.ysn.moncy.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.ysn.moncy.views.base.App
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

object AppInjector {

    fun init(app: App) {
        DaggerAppComponent.builder()
                .application(app)
                .build()
                .inject(app)
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                handleActivity(activity)
            }

            override fun onActivityStarted(p0: Activity?) {
                /* nothing to do in here */
            }

            override fun onActivityResumed(p0: Activity?) {
                /* nothing to do in here */
            }

            override fun onActivityPaused(p0: Activity?) {
                /* nothing to do in here */
            }

            override fun onActivityStopped(p0: Activity?) {
                /* nothing to do in here */
            }

            override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
                /* nothing to do in here */
            }

            override fun onActivityDestroyed(p0: Activity?) {
                /* nothing to do in here */
            }

        })

    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                            object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                                    if (f is Injectable) {
                                        AndroidSupportInjection.inject(f)
                                    }
                                }
                            }, true
                    )
        }
    }

}