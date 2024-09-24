package com.landmuc.create_event

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.landmuc.domain.dto.EventDto
import com.landmuc.domain.dto.NewEventDto
import com.landmuc.domain.extension.toLocalDate
import com.landmuc.domain.repository.EventDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

class CreateEventViewModel(
    private val eventDataRep: EventDataRepository
): ViewModel() {

    private val _eventTitle = MutableStateFlow("")
    val eventTitle = _eventTitle.asStateFlow()

    private val _eventDescription = MutableStateFlow("")
    val eventDescription = _eventDescription.asStateFlow()

    private val now: Instant = Clock.System.now()
    private val _eventDate = MutableStateFlow<LocalDate>( now.toLocalDateTime(TimeZone.currentSystemDefault()).date)
    val eventDate = _eventDate.asStateFlow()

    private val _eventTime = MutableStateFlow<LocalTime>( now.toLocalDateTime(TimeZone.currentSystemDefault()).time )
    val eventTime = _eventTime.asStateFlow()

    private val _eventEndDate = MutableStateFlow<LocalDate>( now.toLocalDateTime(TimeZone.currentSystemDefault()).date)
    val eventEndDate = _eventEndDate.asStateFlow()

    private val _eventEndTime = MutableStateFlow<LocalTime>( now.toLocalDateTime(TimeZone.currentSystemDefault()).time )
    val eventEndTime = _eventEndTime.asStateFlow()

    private val _showDateRangePicker = MutableStateFlow(false)
    val showDateRangePicker = _showDateRangePicker.asStateFlow()

    private val _showTimePicker = MutableStateFlow(false)
    val showTimePicker = _showTimePicker.asStateFlow()

    private val _showEndTimePicker = MutableStateFlow(false)
    val showEndTimePicker = _showEndTimePicker.asStateFlow()

    fun onEventTitleChanged(title: String) {
        _eventTitle.update { title }
    }

    fun onEventDescriptionChanged(description: String) {
        _eventDescription.update { description }
    }

    fun onEventDateChanged(date: Long?) {
        val newDate = date?.toLocalDate() ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
        _eventDate.update { newDate }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun onEventTimeChanged(time: TimePickerState) {
        val newTime = LocalTime(
            hour = time.hour,
            minute = time.minute,
        )
        _eventTime.update { newTime }
    }

    fun onEventEndDateChanged(date: Long?) {
        val newDate = date?.toLocalDate() ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
        _eventEndDate.update { newDate }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun onEventEndTimeChanged(time: TimePickerState) {
        val newTime = LocalTime(
            hour = time.hour,
            minute = time.minute,
        )
        _eventEndTime.update { newTime }
    }

    fun onShowDateRangePickerChanged() {
        _showDateRangePicker.update { !_showDateRangePicker.value }
    }

    fun onShowTimePickerChanged() {
        _showTimePicker.update { !_showTimePicker.value }
    }

    fun onShowEndTimePickerChanged() {
        _showEndTimePicker.update { !_showEndTimePicker.value }
    }

    fun sendCreatedEventToServer() {
        viewModelScope.launch {
            val newEvent = NewEventDto(
                title = _eventTitle.value,
                description = _eventDescription.value,
                eventDate = _eventDate.value,
                eventTime = _eventTime.value,
                eventEndDate = _eventEndDate.value,
                eventEndTime = _eventEndTime.value
            )

            eventDataRep.createEvent(newEvent)
        }
    }
}