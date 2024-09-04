package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDAsStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserDto (
   @Serializable(with = UUIDAsStringSerializer::class)
    val id: UUID,
    val name: String?,
    val surname: String?,
    val email: String,
//    val joinedEventList: List<EventDto>,
//    val adminEventList: List<EventDto>
)