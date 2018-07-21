package com.letsgotoperfection.carsauction.ui.carslist

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.letsgotoperfection.carsauction.R
import com.letsgotoperfection.carsauction.ui.models.Car
import com.letsgotoperfection.carsauction.utils.loadUrl
import kotlinx.android.synthetic.main.item_car.view.*
import kotlinx.android.synthetic.main.item_car_auction_details.view.*
import java.util.concurrent.TimeUnit




/**
 * @author hossam.
 */
class CarsAdapter(private val presenter: CarsPresenter, private val itemClick: (Car) -> Unit)
    : RecyclerView.Adapter<CarsAdapter.CarsListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsListHolder {

        return CarsListHolder((
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_car, parent, false)))
    }

    override fun onBindViewHolder(holder: CarsListHolder, position: Int) {
        holder.bind(presenter.getExistedCars()[position], itemClick)
    }

    override fun getItemCount(): Int {
        return presenter.getCarsCount()
    }

    class CarsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(Car: Car, itemClick: (Car) -> Unit) {
            itemView.setOnClickListener { itemClick(Car) }
            itemView.tvCarName.text = Car.modelEn
            itemView.tvValue1.text = Car.AuctionInfo.lot.toString()
            itemView.tvValue2.text = Car.AuctionInfo.bids.toString()
            itemView.tvValue3.text = setTime(Car.AuctionInfo.endDate.toLong())
            itemView.tvPrice.text = Car.AuctionInfo.currentPrice.toString()
            try {
                itemView.imgCar.loadUrl(Car.image)
            } catch (e: Exception) {
                Log.d(this.javaClass.canonicalName, e.message)
            }
        }
        private fun setTime(millis: Long) : String {
            return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                    TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1))
        }
    }




}
