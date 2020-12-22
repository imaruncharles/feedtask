package com.weexpoindia

import android.icu.util.TimeUnit
import android.util.TimeUtils
import java.util.*

class Util {

    companion object {
        fun timeAgo(oldTime: Long): String {

            val newTime = Date().time

            val seconds =
                java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(newTime.minus(oldTime))
            val minutes =
                java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(newTime.minus(oldTime))
            val hours = java.util.concurrent.TimeUnit.MILLISECONDS.toHours(newTime.minus(oldTime))
            val days = java.util.concurrent.TimeUnit.MILLISECONDS.toDays(newTime.minus(oldTime))

            if (seconds<60){
                return "$seconds sec ago"
            }else if (minutes<60){
                return "$minutes min ago"
            }else if (hours<24){
                return "$hours hr ago"
            }else{
                return "$days days ago"
            }
        }

    }
}