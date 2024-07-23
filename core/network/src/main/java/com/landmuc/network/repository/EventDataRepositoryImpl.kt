package com.landmuc.network.repository

import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.repository.EventDataRepository
import com.landmuc.network.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class EventDataRepositoryImpl(
    private val client: SupabaseClient
): EventDataRepository {
    override suspend fun getEventList(): List<EventDto> {
        return client.supabaseClient.postgrest.from("wms_events").select().decodeList<EventDto>()
    }

    override suspend fun createEvent() {
        TODO("Not yet implemented")
    }

    override suspend fun searchEvent() {
        TODO("Not yet implemented")
    }

}
