package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class StepDto (
//    val stepId: UUID,
    val title: String,
//    val description: String,
//    val createdBy: UserDto,
//    val dateCreated: String,
//    val timeCreated: String,
//    val stepDate: String,
//    val stepTime: String,
    @Serializable(with = UUIDAsStringSerializer::class)
    val eventId: UUID
)

