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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val updateFollowedEventsInEventList: UpdateFollowedEventsInEventList,
    private val eventDataRep: EventDataRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchFilteredEventList: MutableStateFlow<List<Event>> = MutableStateFlow(listOf())
    val searchFilteredEventList = _searchFilteredEventList.asStateFlow()

    private val _followedEventList: MutableStateFlow<List<Event>> = MutableStateFlow(listOf())
    val followedEventList = _followedEventList.asStateFlow()

    init {
        getAllEvents()
        getFollowedEventList()
    }

    fun onSearchQueryChanged(searchQuery: String) {
        _searchQuery.update { searchQuery }
    }

   private fun getAllEvents() {
        viewModelScope.launch {
            val list = eventDataRep.getAllEvents().map { eventDto -> eventDto.toEvent() }
            val followedEventList = eventDataRep.getFollowedEvents().map { eventDto -> eventDto.toEvent() }

            _searchFilteredEventList.update {
                updateFollowedEventsInEventList(
                    allEvents = list,
                    followedEvents = followedEventList
                )
            }
        }
    }

    fun getSearchFilteredEvents(searchQuery: String) {
        viewModelScope.launch {
            val list = eventDataRep.getSearchFilteredEvents(searchQuery).map { eventDto -> eventDto.toEvent() }
            val followedEventList = eventDataRep.getFollowedEvents().map { eventDto -> eventDto.toEvent() }

            _searchFilteredEventList.update {
                updateFollowedEventsInEventList(
                    allEvents = list,
                    followedEvents = followedEventList
                )
            }
        }
    }

    private fun getFollowedEventList() {
        viewModelScope.launch {
            val followedEventList = eventDataRep.getFollowedEvents().map { eventDto -> eventDto.toEvent() }

            _followedEventList.update { followedEventList }
        }
    }

    private fun updateFollowedEventList(eventList: List<Event>) {
        viewModelScope.launch {
            val updatedList = eventList.map { it.id }
            eventDataRep.updateFollowedEventList(updatedList)
        }
    }

    fun followEvent(event: Event) {
        // create a new updated local list of followed events to send to the server
        val updatedList = _followedEventList.value + event

        // update the cell of column "followed_events" of the table "wms_users"
        // of the currently logged in user
        updateFollowedEventList(updatedList)

        // get the new event list from the server
        // this prevents having and using a local version of a followedEventList while simultaneously having a list on the server
        // this ensures that the list the app uses is always the same as the one on the server
        // no weird action while having no/partial internet connection
        if (_searchQuery.value.isEmpty()) getAllEvents() else getSearchFilteredEvents(_searchQuery.value)

        // make sure the _followedEventList is also up to date/the list from the server
        getFollowedEventList()
    }

    fun unfollowEvent(event: Event) {
        // create a new updated local list of followed events to send to the server
        val updatedList = _followedEventList.value - event

        // update the cell of column "followed_events" of the table "wms_users"
        // of the currently logged in user
        updateFollowedEventList(updatedList)

        // get the new event list from the server
        // this prevents having and using a local version of a followedEventList while simultaneously having a list on the server
        // this ensures that the list the app uses is always the same as the one on the server
        // no weird action while having no/partial internet connection
        if (_searchQuery.value.isEmpty()) getAllEvents() else getSearchFilteredEvents(_searchQuery.value)

        // make sure the _followedEventList is also up to date/the list from the server
        getFollowedEventList()
    }

}