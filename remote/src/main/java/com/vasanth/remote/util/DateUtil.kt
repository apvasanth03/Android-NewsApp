package com.vasanth.remote.util

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


/**
 * A class contains date utility methods.
 */
object DateUtil {

    /**
     * Converts string to date with the given dateFormat.
     */
    fun convertStringToDate(dateString: String, dateFormat: String): Date? {
        try {
            val formatter = DateTimeFormatter.ofPattern(dateFormat)
            val zonedDateTime = ZonedDateTime.parse(dateString, formatter)
            val instant = zonedDateTime.toInstant()
            val date = Date(instant.toEpochMilli())
            return date
        } catch (exp: Exception) {
            exp.printStackTrace()
            return null
        }
    }

}