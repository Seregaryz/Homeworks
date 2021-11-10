package com.example.homeworks.service

import android.graphics.Color
import com.example.homeworks.Constans
import com.example.homeworks.R

class WeatherDataSetService {

    fun setTempColor(temp: Double): Int{
        return when (temp){
            in Constans.Color.KEY_VERY_COLD -> R.color.color_very_cold
            in Constans.Color.KEY_COLD -> R.color.color_cold
            in Constans.Color.KEY_NORMAL -> R.color.color_normal
            in Constans.Color.KEY_WARM -> R.color.color_warm
            in Constans.Color.KEY_HOT -> R.color.color_hot
            else -> Color.BLACK
        }
    }

    fun getDirection(deg: Double): String{
        return if (deg >= 337.5 && deg <= 22.5) "N"
        else return if (deg > 22.5 && deg < 67.5) "NE"
        else return if (deg >= 67.5 && deg <= 112.5) "E"
        else return if (deg > 112.5 && deg < 157.5) "SE"
        else return if (deg >= 157.5 && deg <= 202.5) "S"
        else return if (deg > 202.5 && deg < 247.5) "SW"
        else return if (deg >= 247.5 && deg <= 292.5) "W"
        else return "NW"
    }

    fun setImage(all: Int): Int{
        return if(all > 66){
            R.drawable.ic_cloud
        } else if(all < 34){
            R.drawable.ic_sun
        } else R.drawable.ic_forecast
    }
}
