package com.ysn.moncy.utils

import android.app.Activity
import android.view.View
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