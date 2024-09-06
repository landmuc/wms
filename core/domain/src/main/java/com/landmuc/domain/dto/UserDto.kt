package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDListSerializer
import com.landmuc.domain.serializer.UUIDSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserDto (
   @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String?,
    val surname: String?,
    val email: String,
   @SerialName("joined_events")
   @Serializable(with = UUIDListSerializer::class)
    val joinedEvents: List<@Contextual UUID>,
//    val adminEventList: List<EventDto>
)