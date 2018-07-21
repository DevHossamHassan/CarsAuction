package com.letsgotoperfection.chillouttime.ui.movies

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.letsgotoperfection.chillouttime.NavigationManager
import com.letsgotoperfection.chillouttime.R
import com.letsgotoperfection.chillouttime.listeners.OnRecyclerViewScrollToTheEnd
import com.letsgotoperfection.chillouttime.models.Movie
import com.letsgotoperfection.chillouttime.ui.base.BaseFragment
import com.letsgotoperfection.chillouttime.ui.details.DetailsFragment
import com.letsgotoperfection.chillouttime.utils.hide
import com.letsgotoperfection.chillouttime.utils.hideLoadingView
import com.letsgotoperfection.chillouttime.utils.show
import com.letsgotoperfection.chillouttime.utils.showLoadingView
import kotlinx.android.synthetic.main.fragment_movies.*


/**
 * @author hossam.
 */
class MoviesFragment : BaseFragment<MoviesContract.Presenter>(), MoviesContract.View {
    override val fragmentLayoutResourceId = R.layout.fragment_movies
    private lateinit var adapter: MoviesAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun init(savedInstanceState: Bundle?) {
        presenter = MoviesPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setUpRecyclerView()
        setRecyclerViewListeners()
        presenter.onLoadMore()
    }

    private fun setUpRecyclerView() {
        adapter = MoviesAdapter(presenter as MoviesPresenter, { itemClick: Movie -> onItemClick(itemClick) })
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
        recyclerView.isDrawingCacheEnabled = true
        recyclerView.itemAnimator = DefaultItemAnimator()
        gridLayoutManager = if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager(activity.applicationContext, 2)
        } else {
            GridLayoutManager(activity.applicationContext, 4)
        }

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
    }


    private fun setRecyclerViewListeners() {
        recyclerView.addOnScrollListener(
                object : OnRecyclerViewScrollToTheEnd(gridLayoutManager) {
                    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (gridLayoutManager.findLastCompletelyVisibleItemPosition()
                                == adapter.itemCount - 1) {
                            presenter.onLoadMore()
                        }
                    }
                })
        setSwipeRefreshListeners()
    }

    private fun setSwipeRefreshListeners() {
        swipeRefreshLayout.setOnRefreshListener({ presenter.onQueryChanged() })
    }

    override fun showToast(msg: String) {
        Toast.makeText(activity.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun updateDate() {
        adapter.notifyDataSetChanged()
    }

    override fun updateInsertedData(itemCount: Int) {
        adapter.notifyItemRangeInserted(presenter.getMoviesCount() - itemCount, itemCount)
    }

    override fun hideSwipeToRefreshProgressBar() {
        swipeRefreshLayout?.hideLoadingView()
    }

    override fun showSwipeToRefreshProgressBar() {
        swipeRefreshLayout?.showLoadingView()
    }

    override fun showLoadMoreProgressBar() {
        progressBar?.show()
    }

    override fun hideLoadMoreProgressBar() {
        progressBar?.hide()
    }

    private fun onItemClick(movie: Movie) {
        navigateToDetailsFragment(movie)
    }

    override fun navigateToDetailsFragment(movie: Movie) {
        NavigationManager.attach(this.activity, DetailsFragment.newInstance(movie),
                false, "DetailsFragment")
    }
}