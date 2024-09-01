package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDAsStringSerializer
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class EventDto (
    @Serializable(with = UUIDAsStringSerializer::class)
    @SerialName("event_id")
    val eventId: UUID,
    val title: String,
    val description: String? = null,
//    @Serializable(with = UUIDAsStringSerializer::class)
//    val createdBy: UUID,
    @SerialName("date_created")
    val dateCreated: LocalDate,
    @SerialName("time_created")
    val timeCreated: LocalTime,
    @SerialName("event_date")
    val eventDate: LocalDate? = null,
    @SerialName("event_time")
    val eventTime: LocalTime? = null,
//    val stepList: List<StepDto>? = null,
//    val adminList: List<UserDto>? = null,
//    val userList: List<UserDto>? = null
)