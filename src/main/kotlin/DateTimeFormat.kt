package com.samplecode

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class DateTimeFormat {
    companion object {
        fun fromIsoString(str: String): LocalDateTime {
            var instant = Instant.parse(str)
            return LocalDateTime.from(instant.atOffset(ZoneOffset.UTC))
        }

        fun toIsoString(dt: LocalDateTime): String {
            return dt.format(DateTimeFormatter.ISO_DATE_TIME);
        }
    }

}