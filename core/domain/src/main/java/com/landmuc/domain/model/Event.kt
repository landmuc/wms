package com.landmuc.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import java.util.UUID

data class Event (
    val eventId: UUID,
    val title: String,
    val description: String,
//    val createdBy: User,
    val dateCreated: LocalDate,
    val timeCreated: LocalTime,
    val eventDate: LocalDate,
    val eventTime: LocalTime,
//    val stepList: List<Step>,
//    val adminList: List<User>,
//    val userList: List<User>
)