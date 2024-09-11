package com.landmuc.domain.mapper

import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.event.EventStatus
import com.landmuc.domain.model.Event
import com.landmuc.domain.use_case.CheckEventStatus
import com.landmuc.domain.use_case.ValidateEmail
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

fun EventDto.toEvent(): Event {
    val checkEventStatus = CheckEventStatus()
    val eventStatus = checkEventStatus(
        eventDate = eventDate,
        eventTime = eventTime,
        eventEndDate = eventEndDate,
        eventEndTime = eventEndTime
    )

    return Event(
        eventId = eventId,
        title = title,
        description = description ?: "No description",
//        createdBy = createdBy,
        dateCreated = dateCreated,
        timeCreated = timeCreated,
        eventDate = eventDate,
        eventTime = eventTime,
        eventEndDate = eventEndDate,
        eventEndTime = eventEndTime,
        eventStatus = eventStatus
//        stepList = "",
//        adminList = "",
//        userList = ""
    )
}