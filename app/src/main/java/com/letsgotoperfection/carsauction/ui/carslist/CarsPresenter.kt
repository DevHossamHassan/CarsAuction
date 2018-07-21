package com.letsgotoperfection.carsauction.ui.carslist

import android.annotation.SuppressLint
import android.util.Log
import com.letsgotoperfection.carsauction.data.RetrofitProvider
import com.letsgotoperfection.carsauction.ui.base.BasePresenter
import com.letsgotoperfection.carsauction.ui.models.Car
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author hossam.
 */
class CarsPresenter(private val CarsView: CarsContract.View)
    : BasePresenter<CarsContract.View>(CarsView), CarsContract.Presenter {

    override fun getExistedCars(): List<Car> {
        return CarsModel.Cars
    }

    override fun getCarsCount(): Int {
        return CarsModel.Cars.size
    }

    override fun onQueryChanged() {
        getCars()
    }

    override fun onLoadMore() {
        getCars(++CarsModel.currentPage)
    }

    @SuppressLint("CheckResult")
    private fun getCars(page: Int = 1) {
//        if (page > 1) {
//            CarsView.showLoadMoreProgressBar()
//        } else {
//            CarsView.showSwipeToRefreshProgressBar()
//        }
        RetrofitProvider.loadCarAuctions(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    CarsModel.Cars += it.Cars
                    CarsModel.currentPage = it.count
                    CarsView.updateDate()
                    CarsView.hideSwipeToRefreshProgressBar()
                    CarsView.hideLoadMoreProgressBar()
                }, { e ->
                    CarsView.hideSwipeToRefreshProgressBar()
                    CarsView.hideLoadMoreProgressBar()
                    Log.e("CarsPresenter", "Exception Occurred while Carsing = " + e.message)
                })
    }

    override fun destroy() {
        CarsModel.destroy()
    }
}