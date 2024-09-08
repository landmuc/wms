package com.landmuc.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.landmuc.domain.mapper.toEvent
import com.landmuc.domain.model.Event
import com.landmuc.domain.repository.EventDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val eventDataRep: EventDataRepository
): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchFilteredEventList: MutableStateFlow<List<Event>> = MutableStateFlow(listOf())
    val searchFilteredEventList = _searchFilteredEventList.asStateFlow()

    fun onSearchQueryChanged(searchQuery: String) {
        _searchQuery.update { searchQuery }
    }

    fun getAllEvents() {
        viewModelScope.launch {
            val list = eventDataRep.getAllEvents().map { eventDto -> eventDto.toEvent() }
            _searchFilteredEventList.update { list }
        }
    }

    fun getSearchFilteredEvents(searchQuery: String) {
        viewModelScope.launch {
            eventDataRep.getSearchFilteredEvents(searchQuery)
        }
    }
}