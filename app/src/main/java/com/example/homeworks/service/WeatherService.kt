package com.example.homeworks.service

import com.example.homeworks.responce.Weather
import com.example.homeworks.weather.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun weatherByName(@Query("q") name: String): Weather

    @GET("weather")
    suspend fun weatherById(@Query("id") id: Int): Weather

    @GET("find")
    suspend fun weatherOfCitiesInCycle(@Query("lat") lat: Double, @Query("lon") lon: Double,
                                       @Query("cnt") cnt: Int) : WeatherDTO
}
