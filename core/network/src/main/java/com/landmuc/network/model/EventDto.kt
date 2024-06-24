package com.landmuc.network.model

import kotlinx.serialization.Serializable

@Serializable
data class EventDto (
    val title: String,
    val createdBy: String? = null,
    val description: String? = null,
    val eventDate: String? = null,
    val eventTime: String? = null,
    val stepList: List<StepDto>? = null,
    val adminList: List<UserDto>? = null,
    val userList: List<UserDto>? = null
)