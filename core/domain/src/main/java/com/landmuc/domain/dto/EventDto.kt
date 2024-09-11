package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDSerializer
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class EventDto (
    @Serializable(with = UUIDSerializer::class)
    @SerialName("event_id")
    val eventId: UUID,
    val title: String,
    val description: String? = null, //TODO: Delete = null (initial value does not need to be null)
//    @Serializable(with = UUIDAsStringSerializer::class)
//    val createdBy: UUID,
    @SerialName("date_created")
    val dateCreated: LocalDate,
    @SerialName("time_created")
    val timeCreated: LocalTime,
    @SerialName("event_date")
    val eventDate: LocalDate,
    @SerialName("event_time")
    val eventTime: LocalTime,
    @SerialName("event_end_date")
    val eventEndDate: LocalDate,
    @SerialName("event_end_time")
    val eventEndTime: LocalTime,
//    val stepList: List<StepDto>? = null,
//    val adminList: List<UserDto>? = null,
//    val userList: List<UserDto>? = null
)