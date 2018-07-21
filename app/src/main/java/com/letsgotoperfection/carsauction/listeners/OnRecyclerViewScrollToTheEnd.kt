package com.letsgotoperfection.chillouttime.listeners

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * @author hossam.
 */
abstract class OnRecyclerViewScrollToTheEnd
protected constructor(private val linearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var loading = true
    private var previousTotal = 0

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView!!.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        val visibleThreshold = 5
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            loading = true
        }
    }


}
