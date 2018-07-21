package com.letsgotoperfection.chillouttime.ui.movies

import com.letsgotoperfection.chillouttime.models.Movie


/**
 * @author hossam.
 */
object MoviesModel {
    var currentPage = 0
    var movies: List<Movie> = listOf()

    fun destroy() {
        movies = emptyList()
    }
}