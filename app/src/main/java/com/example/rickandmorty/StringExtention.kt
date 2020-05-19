package com.example.rickandmorty

import android.util.Log
import com.example.rickandmorty.Constants.DATE_FORMAT
import com.example.rickandmorty.Constants.DATE_FORMAT_API
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

fun String.formatDate(date: String): String {
    val outputFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    val inputFormat = SimpleDateFormat(DATE_FORMAT_API, Locale.getDefault())

    try {
        val dateInput: Date = inputFormat.parse(date)
        return outputFormat.format(dateInput)
    } catch (ex: Exception) {
        Log.i("Exception", "Data exception ----> ${ex.message}")
    }
    return ""
}