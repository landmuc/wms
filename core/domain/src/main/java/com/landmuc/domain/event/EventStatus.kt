package com.landmuc.domain.event

sealed class EventStatus(val text: String) {
    data object Upcoming: EventStatus("Upcoming")
    data object Ongoing: EventStatus("Ongoing")
    data object Over: EventStatus("Over")
}