package com.landmuc.domain.use_case

import com.landmuc.domain.model.Event
import java.util.UUID

// compares the list of all/search filtered events with the list of followed events
// and changes the value of isFollowed to true in the all/search filtered event list
// if an event id is in the followed events list
class UpdateFollowedEventsInEventList {
    operator fun invoke(
        allEvents: List<Event>,
        followedEventListIds: List<UUID>
    ): List<Event> {

        return allEvents.map { event ->
            event.copy(isFollowed = event.id in followedEventListIds)
        }
    }
}