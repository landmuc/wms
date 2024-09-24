package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDSerializer
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class NewEventDto (
    val title: String,
    val description: String,
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
)