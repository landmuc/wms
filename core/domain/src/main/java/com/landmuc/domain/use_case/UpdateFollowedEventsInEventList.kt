package com.landmuc.domain.use_case

import com.landmuc.domain.model.Event

class UpdateFollowedEventsInEventList {
    operator fun invoke(
        allEvents: List<Event>,
        followedEvents: List<Event>
    ): List<Event> {
        val followedEventIds = followedEvents.map { it.id }

        return allEvents.map { event ->
            event.copy(isFollowed = event.id in followedEventIds)
        }
    }
}