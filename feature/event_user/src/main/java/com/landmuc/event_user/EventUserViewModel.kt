package com.landmuc.event_user

import androidx.lifecycle.ViewModel
import com.landmuc.domain.model.Step
import com.landmuc.domain.repository.EventDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventUserViewModel(
    private val eventDataRep: EventDataRepository
): ViewModel() {
    private val _stepList: MutableStateFlow<List<Step>> = MutableStateFlow(listOf())
    val stepList = _stepList.asStateFlow()

    fun getStepList() {}
}