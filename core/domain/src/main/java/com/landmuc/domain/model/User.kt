package com.landmuc.domain.model

data class User (
    val userID: Int,
    val name: String,
    val surName: String,
    val joinedEventList: List<Event>,
    val adminEventList: List<Event>
)