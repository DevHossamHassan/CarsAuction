package com.letsgotoperfection.carsauction.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author hossam.
 */
object RetrofitProvider {
    private const val BASE_URL = "http://api.emiratesauction.com/v2/"
    private lateinit var auctionsApi: AuctionsApi

    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


    private fun initServices(retrofit: Retrofit) {
        auctionsApi = retrofit.create(AuctionsApi::class.java)
    }

    fun loadCarAuctions(page: Int = 1) =
            auctionsApi.getAuctions(page)
}