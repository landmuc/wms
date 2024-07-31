package com.landmuc.wms.navigation

import com.landmuc.domain.serializer.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

sealed class Route() {
    @Serializable
    object SignInScreen

    @Serializable
    object SignUpScreen

    @Serializable
    object EventListScreen

    @Serializable
    object EventAdminScreen

    @Serializable
    object EventStepAdminScreen

    @Serializable
    data class EventUserScreen(
        val eventTitle: String,
        val eventId: String
    )

    @Serializable
    object EventStepUserScreen
}

