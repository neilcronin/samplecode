package com.samplecode

import java.lang.Integer.max
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

class BusinessHoursCalculator(var business: Business) {
    fun numberOfHoursBetweenDateTimes(startTime: LocalDateTime, endTime: LocalDateTime): Int {

        if (startTime.isAfter(endTime)) {
            return 0
        }

        val startDate = startTime.toLocalDate()
        val endDate = endTime.toLocalDate()


        var hoursOnFirstDay = 0
        if (business.hoursOpenOnDay(startDate).startHourOfDay != -1) {
            val openingHourFirstDay = business.hoursOpenOnDay(startDate).startHourOfDay
            val openingTimeFirstDay = startTime.withHour(openingHourFirstDay)
            val closingHourFirstDay = business.hoursOpenOnDay(startDate).endHourOfDay
            val closingTimeFirstDay = startTime.withHour(closingHourFirstDay)
            hoursOnFirstDay =
                if (startTime.isAfter(closingTimeFirstDay)) {
                    0
                } else {
                    closingHourFirstDay - max(startTime.hour, openingHourFirstDay)
                }
        }

        var hoursOnInterimDays = 0
        var nextInterimDay = startDate.plusDays(1).atStartOfDay().toLocalDate()
        while(nextInterimDay.isBefore(endDate)) {
            val hoursOnThisDay = business.hoursOpenOnDay(startDate.plusDays(1))
            hoursOnInterimDays += if (hoursOnThisDay.startHourOfDay == -1 && hoursOnThisDay.endHourOfDay == -1) {
                0
            } else {
                (hoursOnThisDay.endHourOfDay) - (hoursOnThisDay.startHourOfDay)
            }
            nextInterimDay = nextInterimDay.plusDays(1).atStartOfDay().toLocalDate()
        }


        var hoursOnLastDay = 0
        if (business.hoursOpenOnDay(endDate).startHourOfDay != -1) {
            val openingHourLastDay = business.hoursOpenOnDay(endDate).startHourOfDay
            val openingTimeLastDay = endTime.withHour(openingHourLastDay)
            val closingHourLastDay = business.hoursOpenOnDay(endDate).endHourOfDay
            val closingTimeLastDay = endTime.withHour(closingHourLastDay)
            hoursOnLastDay =
                if (endTime.isBefore(openingTimeLastDay)) {
                    0
                } else {
                    if (endTime.isBefore(closingTimeLastDay)) {
                        endTime.hour - openingHourLastDay
                    } else {
                        closingHourLastDay - openingHourLastDay
                    }
                }
        }
        return hoursOnFirstDay + hoursOnInterimDays + hoursOnLastDay
    }
}

enum class BusinessHours(val startHourOfDay: Int, val endHourOfDay: Int) {
    NINE_TO_FIVE(9, 12+5),
    CLOSED(-1, -1)
}

class Business {
    fun hoursOpenOnDay(day: LocalDate): BusinessHours {
        return when (day.dayOfWeek) {
            DayOfWeek.SUNDAY, DayOfWeek.SATURDAY -> {
                BusinessHours.CLOSED
            }
            else -> {
                BusinessHours.NINE_TO_FIVE
            }
        }
    }
}