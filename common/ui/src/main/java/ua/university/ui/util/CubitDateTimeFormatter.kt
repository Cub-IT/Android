package ua.university.ui.util

import java.text.SimpleDateFormat

object CubitDateTimeFormatter {
    private val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    private val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")

    fun format(date: String): String {
        return formatter.format(parser.parse(date))
    }
}