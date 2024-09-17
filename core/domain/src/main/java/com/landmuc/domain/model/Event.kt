package com.landmuc.domain.model

import com.landmuc.domain.event.EventStatus
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import java.util.UUID

data class Event (
    val id: UUID,
    val title: String,
    val description: String,
//    val createdBy: User,
    val dateCreated: LocalDate,
    val timeCreated: LocalTime,
    val eventDate: LocalDate,
    val eventTime: LocalTime,
    val eventEndDate: LocalDate,
    val eventEndTime: LocalTime,
    val eventStatus: EventStatus,
    val isFollowed: Boolean
//    val stepList: List<Step>,
//    val adminList: List<User>,
)