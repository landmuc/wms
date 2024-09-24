package com.landmuc.domain.extension

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long.toLocalDate(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDate {
    return Instant.fromEpochMilliseconds(this)
        .toLocalDateTime(timeZone)
        .date
}