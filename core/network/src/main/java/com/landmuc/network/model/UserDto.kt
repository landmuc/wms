package com.landmuc.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    val userID: Int,
    val name: String,
    val surName: String,
    val joinedEventList: List<EventDto>,
    val adminEventList: List<EventDto>
)