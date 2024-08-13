package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDAsStringSerializer
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class EventDto (
    @Serializable(with = UUIDAsStringSerializer::class)
    val eventId: UUID,
    val title: String,
    val description: String? = null,
//    @Serializable(with = UUIDAsStringSerializer::class)
//    val createdBy: UUID,
    val dateCreated: LocalDate,
    val timeCreated: LocalTime,
    val eventDate: LocalDate? = null,
    val eventTime: LocalTime? = null,
//    val stepList: List<StepDto>? = null,
//    val adminList: List<UserDto>? = null,
//    val userList: List<UserDto>? = null
)