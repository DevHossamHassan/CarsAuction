package com.letsgotoperfection.carsauction.data

import com.letsgotoperfection.carsauction.ui.models.AuctionResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author hossam.
 */
interface AuctionsApi {
    @GET("carsonline")
    fun getAuctions(@Query("page") count: Int = 1): Flowable<AuctionResponse>
}