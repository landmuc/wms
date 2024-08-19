package com.landmuc.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewUserDto (
    val name: String,
    val surname: String,
    val email: String,
)