package com.letsgotoperfection.carsauction.ui.carslist

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.letsgotoperfection.carsauction.R
import com.letsgotoperfection.carsauction.listeners.OnRecyclerViewScrollToTheEnd
import com.letsgotoperfection.carsauction.ui.base.BaseFragment
import com.letsgotoperfection.carsauction.ui.models.Car
import com.letsgotoperfection.carsauction.utils.hide
import com.letsgotoperfection.carsauction.utils.hideLoadingView
import com.letsgotoperfection.carsauction.utils.show
import com.letsgotoperfection.carsauction.utils.showLoadingView
import kotlinx.android.synthetic.main.fragment_cars.*


/**
 * @author hossam.
 */
class CarsFragment : BaseFragment<CarsContract.Presenter>(), CarsContract.View {
    override val fragmentLayoutResourceId = R.layout.fragment_cars
    private lateinit var adapter: CarsAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun init(savedInstanceState: Bundle?) {
        presenter = CarsPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setUpRecyclerView()
        setRecyclerViewListeners()
        presenter.onLoadMore()
    }

    private fun setUpRecyclerView() {
        adapter = CarsAdapter(presenter as CarsPresenter, { itemClick: Car -> onItemClick(itemClick) })
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
        recyclerView.isDrawingCacheEnabled = true
        recyclerView.itemAnimator = DefaultItemAnimator()
        gridLayoutManager = if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager(activity.applicationContext, 1)
        } else {
            GridLayoutManager(activity.applicationContext, 2)
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
        adapter.notifyItemRangeInserted(presenter.getCarsCount() - itemCount, itemCount)
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

    private fun onItemClick(Car: Car) {
        navigateToDetailsFragment(Car)
    }

    override fun navigateToDetailsFragment(Car: Car) {
//        NavigationManager.attach(this.activity, DetailsFragment.newInstance(Car),
//                false, "DetailsFragment")
    }
}