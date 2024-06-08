package com.reading.novel.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object DateTimeUtil {

    //parse date from 2023-11-28T00:00:00 to Nov 28
    fun parseDateToMonthDay(date: String): String {
        try {
            val dateArr = date.split("-")
            val year = dateArr[0].toInt()
            val month = dateArr[1].toInt()
            val day = dateArr[2].split("T")[0].toInt()

            val monthName = when (month) {
                1 -> "Jan."
                2 -> "Feb."
                3 -> "Mar."
                4 -> "Apr."
                5 -> "May."
                6 -> "Jun."
                7 -> "Jul."
                8 -> "Aug."
                9 -> "Sep."
                10 -> "Oct."
                11 -> "Nov."
                12 -> "Dec."
                else -> ""
            }

            return "$monthName $day"
        } catch (e: Exception) {
            return ""
        }
    }

    // parse date from 2023-11-28T00:00:00 to 28
    fun parseDateToDay(date: String): String {
        return try {
            val dateArr = date.split("-")
            val year = dateArr[0].toInt()
            val month = dateArr[1].toInt()
            val day = dateArr[2].split("T")[0].toInt()

            "$day"
        } catch (e: Exception) {
            ""
        }
    }

    //parse date from 2023-11-28T00:00:00 to November 28, 2023
    fun parseDateToMonthDayYear(date: String): String {
        try {
            val dateArr = date.split("-")
            val year = dateArr[0].toInt()
            val month = dateArr[1].toInt()
            val day = dateArr[2].split("T")[0].toInt()

            val monthName = when (month) {
                1 -> "January"
                2 -> "February"
                3 -> "March"
                4 -> "April"
                5 -> "May"
                6 -> "June"
                7 -> "July"
                8 -> "August"
                9 -> "September"
                10 -> "October"
                11 -> "November"
                12 -> "December"
                else -> ""
            }

            return "$monthName $day, $year"
        } catch (e: Exception) {
            return ""
        }
    }

    fun getCurrentHour(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.HOUR_OF_DAY)
    }

    fun isCurrentDateInRange(startDate: String?, endDate: String?): Boolean {
        if (startDate.isNullOrEmpty() || endDate.isNullOrEmpty())
            return false

        val startDateArr = startDate.split("-")
        val endDateArr = endDate.split("-")

        val startYear = startDateArr[0].toInt()
        val startMonth = startDateArr[1].toInt()
        val startDay = startDateArr[2].split("T")[0].toInt()

        val endYear = endDateArr[0].toInt()
        val endMonth = endDateArr[1].toInt()
        val endDay = endDateArr[2].split("T")[0].toInt()

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        if (currentYear < startYear || currentYear > endYear)
            return false

        if (currentMonth < startMonth || currentMonth > endMonth)
            return false

        if (currentDay < startDay || currentDay > endDay)
            return false

        return true
    }

    fun convertMonthNumberToFullNames(number: String?): String {
        if (number.isNullOrEmpty())
            return ""
        return when (number) {
            "01" -> "January"
            "02" -> "February"
            "03" -> "March"
            "04" -> "April"
            "05" -> "May"
            "06" -> "June"
            "07" -> "July"
            "08" -> "August"
            "09" -> "September"
            "10" -> "October"
            "11" -> "November"
            "12" -> "December"
            else -> ""
        }
    }

    fun isDatePast(dateString: String): Boolean {
        // Define the date format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        return try {
            // Parse the input date string into a Date object
            val inputDate = dateFormat.parse(dateString)

            // Get the current date
            val currentDate = Date()

            // Compare the input date with the current date
            inputDate?.before(currentDate) ?: false
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle the parsing exception as needed
            false
        }
    }
}