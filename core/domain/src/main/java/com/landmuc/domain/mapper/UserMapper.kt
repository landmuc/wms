package com.landmuc.domain.mapper

import com.landmuc.domain.dto.UserDto
import com.landmuc.domain.model.User

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name ?: "No name provided",
        surname = surname ?: "No surname provided",
        email = email
    )
}