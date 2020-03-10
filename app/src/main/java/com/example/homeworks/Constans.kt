package com.example.homeworks

class Constans {
    interface Color{
        companion object{
            val KEY_VERY_COLD = -100.0..-20.0
            val KEY_COLD = -19.9..-5.0
            val KEY_NORMAL = -4.9..10.0
            val KEY_WARM = 10.1..25.0
            val KEY_HOT = 25.1..100.0
        }
    }
}
