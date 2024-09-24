package com.landmuc.domain.repository

import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.dto.NewEventDto
import com.landmuc.domain.dto.StepDto
import java.util.UUID

interface EventDataRepository {
    // regarding events
    suspend fun getAllEvents(): List<EventDto>
    suspend fun getSearchFilteredEvents(searchQuery: String): List<EventDto>
    suspend fun getFollowedEvents(): List<EventDto>
    suspend fun createEvent(event: NewEventDto)
    suspend fun updateFollowedEventList(updatedList: List<UUID>)
    // regarding steps
    suspend fun getStepList(eventId: UUID): List<StepDto>
}