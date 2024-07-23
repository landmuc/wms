package com.landmuc.domain.repository

import com.landmuc.domain.dto.EventDto

interface EventDataRepository {
    suspend fun getEventList(): List<EventDto>
    suspend fun createEvent()
    suspend fun searchEvent()
}