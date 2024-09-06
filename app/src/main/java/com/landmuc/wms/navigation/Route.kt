package com.landmuc.wms.navigation

import kotlinx.serialization.Serializable

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

