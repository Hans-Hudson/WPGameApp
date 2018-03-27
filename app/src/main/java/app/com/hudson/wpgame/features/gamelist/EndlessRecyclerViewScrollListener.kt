package app.com.hudson.wpgame.features.gamelist

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by Hans on 25/03/2018.
 */
abstract class EndlessRecyclerViewScrollListener(var mLayoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

    var visibleThreshold = 5
    var currentPage = 0
    var previousTotalItemCount = 0
    var loading = true
    var startingPageIndex = 0

    init {
        visibleThreshold = visibleThreshold * mLayoutManager.spanCount
    }

    fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    @Override
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        var lastVisibleItemPosition = 0
        var totalItemCount = mLayoutManager.getItemCount()

        lastVisibleItemPosition = ( mLayoutManager ).findLastVisibleItemPosition()

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, recyclerView)
            loading = true
        }
    }

    fun resetState() {
        this.currentPage = this.startingPageIndex
        this.previousTotalItemCount = 0
        this.loading = true
    }

   abstract fun onLoadMore( page : Int, totalCaptureResult: Int, view: RecyclerView?)
}