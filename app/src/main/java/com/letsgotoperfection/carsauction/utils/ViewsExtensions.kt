package com.letsgotoperfection.carsauction.utils

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * @author hossam.
 */
fun View.show() {
    if (isVisible()) return
    this.visibility = View.VISIBLE
}

fun View.hide() {
    if (!isVisible()) return
    this.visibility = View.GONE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun SwipeRefreshLayout.showLoadingView() {
    isRefreshing = true
}

fun SwipeRefreshLayout.hideLoadingView() {
    isRefreshing = false
}

fun ImageView.loadUrl(url: String) {
    val newUrl = url.replace("[w]", "300").replace("[h]", "300")
    Glide.with(context)
            .load(newUrl)
            .into(this)
}
