package com.example.cleanarchitectureexample.utils

import java.text.SimpleDateFormat
import java.util.*

object Time {

    fun currentTime(): String {
        val date = Date()
        val sdf = SimpleDateFormat("dd.MMM.yyyy\n HH:mm", Locale.getDefault())
        return sdf.format(date)
    }
}