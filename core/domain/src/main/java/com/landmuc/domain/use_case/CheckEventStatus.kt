package com.landmuc.domain.use_case

import com.landmuc.domain.event.EventStatus
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

class CheckEventStatus {
    operator fun invoke(
        eventDate: LocalDate,
        eventTime: LocalTime,
        eventEndDate: LocalDate,
        eventEndTime: LocalTime
    ):EventStatus {
        val dateNow: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val clockSystemNow: Instant = Clock.System.now()
        val timeNow: LocalTime = clockSystemNow.toLocalDateTime(TimeZone.currentSystemDefault()).time
        val timeZone = TimeZone.currentSystemDefault()

        val now = dateNow.atTime(timeNow).toInstant(timeZone)
        val eventStart = eventDate.atTime(eventTime).toInstant(timeZone)
        val eventEnd = eventEndDate.atTime(eventEndTime).toInstant(timeZone)

        return when {
            now < eventStart -> EventStatus.Upcoming
            now >= eventStart && now < eventEnd -> EventStatus.Ongoing
            else -> EventStatus.Over
        }
    }
}