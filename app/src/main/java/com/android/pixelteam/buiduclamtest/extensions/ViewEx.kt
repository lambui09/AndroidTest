package com.android.pixelteam.buiduclamtest.extensions

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.show(isShow: Boolean = true) {
    visibility = if (isShow) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.invisible() {
    visibility = View.INVISIBLE
}