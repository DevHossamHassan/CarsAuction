package com.letsgotoperfection.carsauction.ui.carslist

import com.letsgotoperfection.carsauction.ui.models.Car


/**
 * @author hossam.
 */
object CarsModel {
    var currentPage = 0
    var Cars: List<Car> = listOf()

    fun destroy() {
        Cars = emptyList()
    }
}