package com.samplecode

import java.time.LocalDateTime

class AgeValidator(val minAgeInYears: Int, val systemDateTime: LocalDateTime = LocalDateTime.now()) {

    fun isOfAge(dob: String): Boolean {
        if (DateTimeFormat.fromDateString(dob).plusYears(minAgeInYears.toLong()).isBefore(systemDateTime.plusDays(1))) {
            return true
        } else {
            return false
        }
    }

}