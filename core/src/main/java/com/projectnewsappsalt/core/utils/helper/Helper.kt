package com.projectnewsappsalt.core.utils.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    val outputFormat = SimpleDateFormat("yyyy/MM/dd ", Locale.US)
    try {
        return outputFormat.format(inputFormat.parse(date) ?: "")
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}