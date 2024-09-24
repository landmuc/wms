package com.landmuc.network.repository

import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.dto.NewEventDto
import com.landmuc.domain.dto.StepDto
import com.landmuc.domain.dto.UserDto
import com.landmuc.domain.model.Step
import com.landmuc.domain.repository.EventDataRepository
import com.landmuc.network.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import java.util.UUID

class EventDataRepositoryImpl(
    private val client: SupabaseClient
) : EventDataRepository {
    // regarding events
    override suspend fun getAllEvents(): List<EventDto> {
        return client.supabaseClient.postgrest.from("wms_events").select().decodeList<EventDto>()
    }

    override suspend fun getSearchFilteredEvents(searchQuery: String): List<EventDto> {
        return client.supabaseClient.postgrest.from("wms_events").select() {
            filter {
                ilike("title", "%$searchQuery%")

                // use or {
                //   like(...)
                //   like(...)
                // }  for multiple filters
            }
        }.decodeList<EventDto>()
    }

    override suspend fun getFollowedEvents(): List<EventDto> {
        val user = client.supabaseClient.auth.currentUserOrNull()
        val userIdAsString = user?.id
        val userIdAsUUID = UUID.fromString(userIdAsString)

        val currentUserData = client.supabaseClient.postgrest.from("wms_users").select() {
            filter {
                eq("id", userIdAsUUID)
            }
        }.decodeSingle<UserDto>()

        val followedEvents = currentUserData.followedEvents

        return client.supabaseClient.postgrest.from("wms_events").select() {
            filter {
                isIn("event_id", followedEvents)
            }
        }.decodeList<EventDto>()
    }


    override suspend fun createEvent(event: NewEventDto) {
        client.supabaseClient.postgrest.from("wms_events").insert(event)
    }

    override suspend fun updateFollowedEventList(updatedList: List<UUID>) {
        val user = client.supabaseClient.auth.currentUserOrNull()
        val userIdAsString = user?.id
        val userIdAsUUID = UUID.fromString(userIdAsString)

        val serializableList = updatedList.map { it.toString() }

        client.supabaseClient.from("wms_users").update(
            {
                set("followed_events", serializableList )
            }
        ) {
            filter {
                eq("id", userIdAsUUID)
            }
        }
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
