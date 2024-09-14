package com.landmuc.domain.dto

import com.landmuc.domain.serializer.UUIDListSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UUIDListDto(
    @Serializable(with = UUIDListSerializer::class)
    val uuidList: List<@Contextual UUID>
)