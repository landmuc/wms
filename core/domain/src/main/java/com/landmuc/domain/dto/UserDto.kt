package com.landmuc.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    val userID: Int,
    val name: String,
    val surname: String,
    val email: String,
    val joinedEventList: List<EventDto>,
    val adminEventList: List<EventDto>
)