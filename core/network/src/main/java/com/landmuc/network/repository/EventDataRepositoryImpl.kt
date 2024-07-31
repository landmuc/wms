package com.landmuc.network.repository

import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.dto.StepDto
import com.landmuc.domain.model.Step
import com.landmuc.domain.repository.EventDataRepository
import com.landmuc.network.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import java.util.UUID

class EventDataRepositoryImpl(
    private val client: SupabaseClient
): EventDataRepository {
    // regarding events
    override suspend fun getEventList(): List<EventDto> {
        return client.supabaseClient.postgrest.from("wms_events").select().decodeList<EventDto>()
    }

    override suspend fun createEvent() {
        TODO("Not yet implemented")
    }

    override suspend fun searchEvent() {
        TODO("Not yet implemented")
    }

    // regarding steps
    override suspend fun getStepList(eventId: UUID): List<StepDto> {
        return client.supabaseClient.postgrest.from("wms_steps").select() {
            filter {
                Step::eventId eq eventId
            }
        }.decodeList<StepDto>()
    }

}
