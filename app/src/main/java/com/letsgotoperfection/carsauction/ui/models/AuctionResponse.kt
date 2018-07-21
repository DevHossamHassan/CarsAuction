package com.letsgotoperfection.carsauction.ui.models

data class AuctionResponse(
        val alertEn: String,
        val alertAr: String,
        val RefreshInterval: Int,
        val Ticks: String,
        val count: Int,
        val endDate: Int,
        val sortOption: String,
        val sortDirection: String,
        val Cars: List<Car>
)