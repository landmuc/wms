package com.landmuc.domain.mapper

import com.landmuc.domain.dto.StepDto
import com.landmuc.domain.model.Step
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

fun StepDto.toStep(): Step {
    return Step(
//        stepId = stepId,
        title = title,
        description = description,
//        createdBy = createdBy,
//        dateCreated = dateCreated,
//        timeCreated = timeCreated,
        stepDate = stepDate ?: LocalDate(12,12,12),
        stepTime = stepTime ?: LocalTime(12,12,12),
        eventId = eventId,
        isOngoing = false
    )
}