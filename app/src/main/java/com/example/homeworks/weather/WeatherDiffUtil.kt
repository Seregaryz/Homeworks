package com.example.homeworks.weather

import androidx.recyclerview.widget.DiffUtil
import com.example.homeworks.responce.Weather

class WeatherDiffUtil(
    private var oldItems: List<Weather>,
    private var newItems: List<Weather>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].name == newItems[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}
