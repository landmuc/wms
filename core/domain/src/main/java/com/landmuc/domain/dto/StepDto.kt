package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDAsStringSerializer
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class StepDto (
//    val stepId: UUID,
    val title: String,
    val description: String,
//    val createdBy: UserDto,
//    val dateCreated: String,
//    val timeCreated: String,
    val stepDate: LocalDate? = null,
    val stepTime: LocalTime? = null,
    @Serializable(with = UUIDAsStringSerializer::class)
    val eventId: UUID
)

