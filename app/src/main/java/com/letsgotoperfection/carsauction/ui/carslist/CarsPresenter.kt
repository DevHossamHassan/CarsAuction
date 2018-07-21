package com.letsgotoperfection.chillouttime.ui.movies

import android.annotation.SuppressLint
import android.util.Log
import com.letsgotoperfection.chillouttime.data.RetrofitProvider
import com.letsgotoperfection.chillouttime.models.Movie
import com.letsgotoperfection.chillouttime.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author hossam.
 */
class MoviesPresenter(private val MoviesView: MoviesContract.View)
    : BasePresenter<MoviesContract.View>(MoviesView), MoviesContract.Presenter {

    override fun getExistedMovies(): List<Movie> {
        return MoviesModel.movies
    }

    override fun getMoviesCount(): Int {
        return MoviesModel.movies.size
    }

    override fun onQueryChanged() {
        getMovies()
    }

    override fun onLoadMore() {
        getMovies(++MoviesModel.currentPage)
    }

    @SuppressLint("CheckResult")
    private fun getMovies(page: Int = 1) {
        if (page > 1) {
            MoviesView.showLoadMoreProgressBar()
        } else {
            MoviesView.showSwipeToRefreshProgressBar()
        }
        RetrofitProvider.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    MoviesModel.movies += it.results
                    MoviesModel.currentPage = it.page
                    MoviesView.updateDate()
                    MoviesView.hideSwipeToRefreshProgressBar()
                    MoviesView.hideLoadMoreProgressBar()
                }, { e ->
                    MoviesView.hideSwipeToRefreshProgressBar()
                    MoviesView.hideLoadMoreProgressBar()
                    Log.e("MoviesPresenter", "Exception Occurred while Moviesing = " + e.message)
                })
    }

    override fun destroy() {
        MoviesModel.destroy()
    }
}