package com.landmuc.domain.model

data class Event (
    val eventId: String,
    val title: String,
    val description: String,
    val createdBy: User,
    val dateCreated: String,
    val timeCreated: String,
    val eventDate: String,
    val eventTime: String,
    val stepList: List<Step>,
    val adminList: List<User>,
    val userList: List<User>
)