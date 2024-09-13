package com.landmuc.search

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
    }

    fun onSearchQueryChanged(searchQuery: String) {
        _searchQuery.update { searchQuery }
    }

    fun getAllEvents() {
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


}