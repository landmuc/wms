package com.landmuc.domain.event

sealed class EventStatus() {
    data object Upcoming: EventStatus()
    data object Ongoing: EventStatus()
    data object Over: EventStatus()
}