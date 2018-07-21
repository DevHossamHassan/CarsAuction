package com.letsgotoperfection.chillouttime.data

import com.letsgotoperfection.chillouttime.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author hossam.
 */
object RetrofitProvider {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private lateinit var moviesApi: MoviesApi

    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build()
    }


    private fun initServices(retrofit: Retrofit) {
        moviesApi = retrofit.create(MoviesApi::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor({ chain ->
                    var request = chain.request()
                    val url = request.url().newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY).build()
                    request = request.newBuilder().url(url).build()
                    chain.proceed(request)
                })
                .build()
    }

    fun loadMovies(page: Int = 1) =
            moviesApi.getPlayingMovies(page)
}