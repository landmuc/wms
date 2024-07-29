package com.landmuc.domain.mapper

import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.model.Event

fun EventDto.toEvent(): Event {
    return Event(
        eventId = eventId,
        title = title,
        description = description ?: "No description",
//        createdBy = createdBy,
//        dateCreated = dateCreated,
//        timeCreated = timeCreated,
//        eventDate = eventDate,
//        eventTime = eventTime,
//        stepList = "",
//        adminList = "",
//        userList = ""
    )
}