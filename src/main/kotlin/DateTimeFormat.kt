package com.samplecode

import io.ktor.util.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class DateTimeFormat {
    companion object {
        fun fromIsoString(str: String): LocalDateTime {
            val instant = Instant.parse(str)
            return LocalDateTime.from(instant.atOffset(ZoneOffset.UTC))
        }

        fun fromDateString(str: String): LocalDateTime {
            val parser = DateTimeFormatter.ISO_LOCAL_DATE
            return LocalDate.parse(str, parser).atStartOfDay()
        }

        fun toIsoString(dt: LocalDateTime): String {
            return dt.format(DateTimeFormatter.ISO_DATE_TIME);
        }
    }

}