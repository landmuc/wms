package com.landmuc.domain.mapper

import com.landmuc.domain.dto.StepDto
import com.landmuc.domain.model.Step

fun StepDto.toStep(): Step {
    return Step(
//        stepId = stepId,
        title = title,
//        description = description,
//        createdBy = createdBy,
//        dateCreated = dateCreated,
//        timeCreated = timeCreated,
//        stepDate = stepDate,
//        stepTime = stepTime,
//        eventId = eventId
    )
}