package com.landmuc.domain.model

import java.util.UUID

data class Event (
    val eventId: UUID,
    val title: String,
    val description: String,
//    val createdBy: User,
//    val dateCreated: String,
//    val timeCreated: String,
//    val eventDate: String,
//    val eventTime: String,
//    val stepList: List<Step>,
//    val adminList: List<User>,
//    val userList: List<User>
)