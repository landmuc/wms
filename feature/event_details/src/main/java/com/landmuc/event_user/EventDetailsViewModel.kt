package com.landmuc.event_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.landmuc.domain.mapper.toEvent
import com.landmuc.domain.mapper.toStep
import com.landmuc.domain.model.Event
import com.landmuc.domain.model.Step
import com.landmuc.domain.repository.EventDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class EventDetailsViewModel(
    private val eventDataRep: EventDataRepository
): ViewModel() {
    private val _stepList: MutableStateFlow<List<Step>> = MutableStateFlow(listOf())
    val stepList = _stepList.asStateFlow()

    private val _event: MutableStateFlow<Event?> = MutableStateFlow(null)
    val event = _event.asStateFlow()

    fun getStepList(eventId: UUID) {
        viewModelScope.launch {
            val list = eventDataRep.getStepList(eventId).map { stepDto -> stepDto.toStep() }
            _stepList.update { list }
        }
    }

    fun getEvent(eventId: UUID) {
        viewModelScope.launch {
            val eventDto = eventDataRep.getEvent(eventId)
            _event.update { eventDto.toEvent() }
        }
    }
}