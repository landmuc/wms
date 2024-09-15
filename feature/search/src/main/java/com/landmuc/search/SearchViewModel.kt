package com.landmuc.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.mapper.toEvent
import com.landmuc.domain.model.Event
import com.landmuc.domain.repository.EventDataRepository
import com.landmuc.domain.use_case.UpdateFollowedEventsInEventList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class SearchViewModel(
    private val updateFollowedEventsInEventList: UpdateFollowedEventsInEventList,
    private val eventDataRep: EventDataRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchFilteredEventList: MutableStateFlow<List<Event>> = MutableStateFlow(listOf())
    val searchFilteredEventList = _searchFilteredEventList.asStateFlow()

    // Local cache for followed event Ids
    private val _followedEventIds = MutableStateFlow<List<UUID>>(listOf())

    init {
        getFollowedEventList()
        getAllEvents()
    }

    fun onSearchQueryChanged(searchQuery: String) {
        _searchQuery.update { searchQuery }
    }

    private fun getAllEvents() {
        viewModelScope.launch {
            if (_followedEventIds.value.isEmpty()) {
                val list = eventDataRep.getAllEvents().map { eventDto -> eventDto.toEvent() }
                val followedEventList = eventDataRep.getFollowedEvents().map { eventDto -> eventDto.toEvent() }
                val ids = followedEventList.map { it.id }

                _searchFilteredEventList.update {
                    updateFollowedEventsInEventList(
                        allEvents = list,
                        followedEventListIds = ids
                    )
                }
            } else {
                val list = eventDataRep.getAllEvents().map { eventDto -> eventDto.toEvent() }

                _searchFilteredEventList.update {
                    updateFollowedEventsInEventList(
                        allEvents = list,
                        followedEventListIds = _followedEventIds.value
                    )
                }
            }
        }
    }

    fun getSearchFilteredEvents(searchQuery: String) {
        viewModelScope.launch {
            val list = eventDataRep.getSearchFilteredEvents(searchQuery).map { eventDto -> eventDto.toEvent() }

            _searchFilteredEventList.update {
                updateFollowedEventsInEventList(
                    allEvents = list,
                    followedEventListIds = _followedEventIds.value
                )
            }
        }
    }

    private fun getFollowedEventList() {
        viewModelScope.launch {
            val followedEventList = eventDataRep.getFollowedEvents().map { eventDto -> eventDto.toEvent() }
            val ids = followedEventList.map { it.id }
            _followedEventIds.update { ids }
            Log.d("TESTDEBUG", "FollowedEventList from Server: $ids")
        }
    }

    private fun updateFollowedEventList(eventListIds: List<UUID>) {
        viewModelScope.launch {
            // update followed event id list on the server of the currently logged in user
            eventDataRep.updateFollowedEventList(eventListIds)
            // use delay so getFollowedEventList() does not fetch the old data before updateFollowedEventList() could update the data on the server
            delay(500) //TODO: Is there a better option?
            // after updating on the server, fetch the latest list of followed events
            getFollowedEventList()
        }
    }

    fun followEvent(event: Event) {
        viewModelScope.launch {
            // create a new updated local list of followed event ids to send to the server and update ui
            _followedEventIds.update { _followedEventIds.value + event.id }
            Log.d("TESTDEBUG", "ADD LIST: ${_followedEventIds.value}")

            // update ui with local list of followed event ids
            _searchFilteredEventList.update {
                updateFollowedEventsInEventList(
                    allEvents = _searchFilteredEventList.value,
                    followedEventListIds = _followedEventIds.value
                )
            }

            // update the cell of column "followed_events" of the table "wms_users" of the currently logged in user
            updateFollowedEventList(_followedEventIds.value)
        }
    }

    fun unfollowEvent(event: Event) {
        viewModelScope.launch {
            // create a new updated local list of followed event ids to send to the server and update ui
            _followedEventIds.update { _followedEventIds.value - event.id }
            Log.d("TESTDEBUG", "ADD LIST: ${_followedEventIds.value}")

            // update ui with local list of followed event ids
            _searchFilteredEventList.update {
                updateFollowedEventsInEventList(
                    allEvents = _searchFilteredEventList.value,
                    followedEventListIds = _followedEventIds.value
                )
            }

            // update the cell of column "followed_events" of the table "wms_users" of the currently logged in user
            updateFollowedEventList(_followedEventIds.value)
        }
    }

}