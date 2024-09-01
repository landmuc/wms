package com.landmuc.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import java.util.UUID

data class Step (
//    val stepId: UUID,
    val title: String,
    val description: String,
//    val createdBy: User,
//    val dateCreated: String,
//    val timeCreated: String,
    val stepDate: LocalDate,
    val stepTime: LocalTime,
    val eventId: UUID,
    val isOngoing: Boolean
)