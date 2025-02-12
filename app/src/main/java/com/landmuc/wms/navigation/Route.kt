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
    data class EventDetailsScreen(
        val eventTitle: String,
        val eventId: String
    )

    @Serializable
    object EventStepUserScreen

    @Serializable
    object SearchScreen

    @Serializable
    object CreateEventScreen

    @Serializable
    object StepDetailsScreen
}

