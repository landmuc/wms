package com.landmuc.event_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.landmuc.domain.mapper.toEvent
import com.landmuc.domain.model.Event
import com.landmuc.domain.repository.EventDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventListViewModel(
    private val eventDataRep: EventDataRepository
): ViewModel() {

    private val _eventList: MutableStateFlow<List<Event>> = MutableStateFlow(listOf())
    val eventList = _eventList.asStateFlow()

    fun getEventList() {
        viewModelScope.launch {
            val list = eventDataRep.getAllEvents().map { eventDto -> eventDto.toEvent() }
            _eventList.update { list }
        }
    }

    fun getFollowedEvents() {
        viewModelScope.launch {
            val list = eventDataRep.getFollowedEvents().map { eventDto -> eventDto.toEvent() }
            _eventList.update { list }
        }
    }
}