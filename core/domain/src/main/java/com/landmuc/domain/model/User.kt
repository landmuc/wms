package com.landmuc.domain.model

data class User (
    val userID: Int,
    val name: String,
    val surname: String,
    val email: String,
    val joinedEventList: List<Event>,
    val adminEventList: List<Event>
)