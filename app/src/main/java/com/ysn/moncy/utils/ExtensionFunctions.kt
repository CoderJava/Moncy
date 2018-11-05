package com.ysn.moncy.utils

import android.app.Activity
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.ysn.moncy.R
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

fun Activity.showSnackbarMessage(message: String) {
    snackbar(find(android.R.id.content), message)
}

fun Activity.showLongSnackbarMessage(message: String) {
    longSnackbar(find(android.R.id.content), message)
}

fun Activity.showToastMessage(message: String) {
    toast(message)
}

fun Activity.showLongToastMessage(message: String) {
    longToast(message)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun showLoading(viewLoading: View, viewContent: ConstraintLayout) {
    viewLoading.visible()
    viewContent.gone()
}

fun Activity.hideLoading(viewLoading: View, viewContent: ConstraintLayout) {
    val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
    fadeOut.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
            /* nothing to do in here */
        }

        override fun onAnimationEnd(animation: Animation?) {
            viewContent.visible()
            viewLoading.gone()
        }

        override fun onAnimationStart(animation: Animation?) {
            /* nothing to do in here */
        }
    })
    viewLoading.startAnimation(fadeOut)
}