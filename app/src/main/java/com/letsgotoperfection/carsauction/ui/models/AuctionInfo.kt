package com.letsgotoperfection.carsauction.ui.models

data class AuctionInfo(
        val bids: Int,
        val endDate: Int,
        val endDateEn: String,
        val endDateAr: String,
        val currencyEn: String,
        val currencyAr: String,
        val currentPrice: Int,
        val minIncrement: Int,
        val lot: Int,
        val priority: Int,
        val VATPercent: Int,
        val isModified: Int,
        val itemid: Int,
        val iCarId: Int,
        val iVinNumber: String
)