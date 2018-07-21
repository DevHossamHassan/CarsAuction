package com.letsgotoperfection.carsauction.ui.models

data class Car(
        val carID: Int,
        val image: String,
        val descriptionAr: String,
        val descriptionEn: String,
        val imgCount: Int,
        val sharingLink: String,
        val sharingMsgEn: String,
        val sharingMsgAr: String,
        val mileage: String,
        val makeID: Int,
        val modelID: Int,
        val bodyId: Int,
        val year: Int,
        val makeEn: String,
        val makeAr: String,
        val modelEn: String,
        val modelAr: String,
        val bodyEn: String,
        val bodyAr: String,
        val AuctionInfo: AuctionInfo
)