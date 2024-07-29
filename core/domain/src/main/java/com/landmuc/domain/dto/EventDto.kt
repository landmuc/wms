package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDAsStringSerializer
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
//    val dateCreated: String,
//    val timeCreated: String,
//    val eventDate: String? = null,
//    val eventTime: String? = null,
//    val stepList: List<StepDto>? = null,
//    val adminList: List<UserDto>? = null,
//    val userList: List<UserDto>? = null
)