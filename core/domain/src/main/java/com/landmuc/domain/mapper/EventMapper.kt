package com.landmuc.domain.mapper

import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.model.Event
import com.landmuc.domain.use_case.CheckEventStatus

fun EventDto.toEvent(): Event {
    val checkEventStatus = CheckEventStatus()
    val eventStatus = checkEventStatus(
        eventDate = eventDate,
        eventTime = eventTime,
        eventEndDate = eventEndDate,
        eventEndTime = eventEndTime
    )

    return Event(
        id = eventId,
        title = title,
        description = description ?: "No description",
//        createdBy = createdBy,
        dateCreated = dateCreated,
        timeCreated = timeCreated,
        eventDate = eventDate,
        eventTime = eventTime,
        eventEndDate = eventEndDate,
        eventEndTime = eventEndTime,
        eventStatus = eventStatus,
        isFollowed = false
//        stepList = "",
//        adminList = "",
//        userList = ""
    )
}