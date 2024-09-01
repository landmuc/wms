package com.landmuc.domain.dto

import kotlinx.serialization.Serializable

// not used right now
// TODO: Delete?
@Serializable
data class NewUserDto (
    val name: String,
    val surname: String,
    val email: String,
)