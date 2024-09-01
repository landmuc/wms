package com.landmuc.domain.mapper

import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.model.Event
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

fun EventDto.toEvent(): Event {
    return Event(
        eventId = eventId,
        title = title,
        description = description ?: "No description",
//        createdBy = createdBy,
        dateCreated = dateCreated,
        timeCreated = timeCreated,
        eventDate = eventDate ?: LocalDate(12, 12, 12),
        eventTime = eventTime ?: LocalTime(12, 12, 12),
        isOngoing = false,
//        stepList = "",
//        adminList = "",
//        userList = ""
    )
}