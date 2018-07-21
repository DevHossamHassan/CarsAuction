package com.letsgotoperfection.carsauction.ui.carslist

import android.app.Fragment
import com.letsgotoperfection.carsauction.ui.base.BaseContract
import com.letsgotoperfection.carsauction.ui.models.Car


/**
 * @author hossam.
 */
class CarsContract : BaseContract {

    interface View : BaseContract.View<Fragment> {
        fun showToast(msg: String)
        fun updateDate()
        fun updateInsertedData(itemCount: Int)
        fun hideSwipeToRefreshProgressBar()
        fun showSwipeToRefreshProgressBar()
        fun hideLoadMoreProgressBar()
        fun showLoadMoreProgressBar()
        fun navigateToDetailsFragment(Car: Car)
    }

    interface Presenter : BaseContract.Presenter {
        fun getExistedCars(): List<Car>
        fun getCarsCount(): Int
        fun onQueryChanged()
        fun onLoadMore()
        fun destroy()
    }
}