package com.landmuc.network.model

import kotlinx.serialization.Serializable

@Serializable
data class StepDto (
    val stepId: String,
    val title: String,
    val description: String,
    val createdBy: UserDto,
    val dateCreated: String,
    val timeCreated: String,
    val stepDate: String,
    val stepTime: String
)