package com.landmuc.domain.model

import java.util.UUID

data class User (
    val id: UUID,
    val name: String,
    val surname: String,
    val email: String,
//    val followedEvents: List<Event>,
//    val adminEventList: List<Event>
)