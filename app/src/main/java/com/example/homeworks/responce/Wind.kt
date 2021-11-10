package com.example.homeworks.responce


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    var deg: Double,
    @SerializedName("gust")
    var gust: Double,
    @SerializedName("speed")
    var speed: Double
)
