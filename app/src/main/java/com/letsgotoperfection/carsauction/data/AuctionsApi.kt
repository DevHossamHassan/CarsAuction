package com.letsgotoperfection.chillouttime.data

import com.letsgotoperfection.chillouttime.models.Results
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author hossam.
 */
interface MoviesApi {
    @GET("movie/now_playing")
    fun getPlayingMovies(@Query("page") count: Int = 1): Flowable<Results>
}