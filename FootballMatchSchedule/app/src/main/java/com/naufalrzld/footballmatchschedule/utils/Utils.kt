package com.naufalrzld.footballmatchschedule.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun toGMTFormat(date: String?, time: String?): Date {
    val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime)
}

fun toGMTFormat2(date: String?, time: String): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime)
}