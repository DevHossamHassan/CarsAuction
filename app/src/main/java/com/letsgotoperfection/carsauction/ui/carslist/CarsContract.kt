package com.letsgotoperfection.chillouttime.ui.movies

import android.app.Fragment
import com.letsgotoperfection.chillouttime.models.Movie
import com.letsgotoperfection.chillouttime.ui.base.BaseContract


/**
 * @author hossam.
 */
class MoviesContract : BaseContract {

    interface View : BaseContract.View<Fragment> {
        fun showToast(msg: String)
        fun updateDate()
        fun updateInsertedData(itemCount: Int)
        fun hideSwipeToRefreshProgressBar()
        fun showSwipeToRefreshProgressBar()
        fun hideLoadMoreProgressBar()
        fun showLoadMoreProgressBar()
        fun navigateToDetailsFragment(movie: Movie)
    }

    interface Presenter : BaseContract.Presenter {
        fun getExistedMovies(): List<Movie>
        fun getMoviesCount(): Int
        fun onQueryChanged()
        fun onLoadMore()
        fun destroy()
    }
}