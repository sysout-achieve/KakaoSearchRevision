package com.gunt.kakaosearchrevision.ui.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(private val endlessCnt: Int) : RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        if (visibleItemCount == 0) {
            previousTotal = 0
        }
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        val visibleThreshold = endlessCnt
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {

            onLoadMore()
            loading = true
        }
    }

    abstract fun onLoadMore()
}
