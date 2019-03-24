package com.vasanth.presentation.util

import java.text.SimpleDateFormat
import java.util.*


/**
 * A class contains date utility methods.
 *
 * @author Vasanth
 */
object DateUtil {

    /**
     * Convert date to string with the given dateFormat.
     */
    fun convertDateToString(date: Date, dateFormat: String): String {
        val sdfFormat = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        return sdfFormat.format(date)
    }
}
