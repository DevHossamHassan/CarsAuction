package com.letsgotoperfection.chillouttime.utils

import android.graphics.Bitmap
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.letsgotoperfection.chillouttime.listeners.OnProminentColorReady


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
    Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/$url")
            .into(this)
}

fun ImageView.loadUrl(url: String, onProminentColorReady: OnProminentColorReady) {
    Glide.with(context)
            .asBitmap()
            .load("https://image.tmdb.org/t/p/w500/$url")
            .listener(object : RequestListener<Bitmap> {
                override fun onResourceReady(
                        resource: Bitmap?, model: Any?, target: Target<Bitmap>?,
                        dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    if (resource != null) {
                        val p = Palette.from(resource).generate()
                        // Pick one of the swatches
                        val vibrant = p.vibrantSwatch
                        if (vibrant != null) {
                            onProminentColorReady.onProminentColorReady(vibrant)
                        }
                    }
                    return false
                }

                override fun onLoadFailed(
                        e: GlideException?, model: Any?,
                        target: com.bumptech.glide.request.target.Target<Bitmap>?,
                        isFirstResource: Boolean): Boolean {
                    return false
                }
            })
            .into(this)
}

fun ImageView.loadUrl(url: String, textView: TextView) {
    Glide.with(context)
            .asBitmap()
            .load("https://image.tmdb.org/t/p/w500/$url")
            .listener(object : RequestListener<Bitmap> {
                override fun onResourceReady(
                        resource: Bitmap?, model: Any?, target: Target<Bitmap>?,
                        dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    if (resource != null) {
                        val p = Palette.from(resource).generate()
                        // Pick one of the swatches
                        val vibrant = p.vibrantSwatch
                        if (vibrant != null) {
                            // Set the background color of a layout based on the vibrant color
                            textView.setBackgroundColor(vibrant.rgb)
                            // Update the title TextView with the proper text color
                            textView.setTextColor(vibrant.titleTextColor)
                        }
                    }
                    return false
                }

                override fun onLoadFailed(
                        e: GlideException?, model: Any?,
                        target: com.bumptech.glide.request.target.Target<Bitmap>?,
                        isFirstResource: Boolean): Boolean {
                    return false
                }
            })
            .into(this)
}


