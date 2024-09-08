package com.landmuc.wms.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.landmuc.authentication.sign_in.SignInScreen
import com.landmuc.authentication.sign_up.SignUpScreen
import com.landmuc.event_admin.EventAdminScreen
import com.landmuc.event_list.EventListScreen
import com.landmuc.event_user.EventUserScreen
import com.landmuc.search.SearchScreen

@Composable
fun RootNavigation() {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.SignInScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.SignInScreen> {
                SignInScreen(
                    onSuccessfulGoogleLogIn = { navController.navigate(Route.EventListScreen) },
                    onSignInClick = { navController.navigate(Route.EventListScreen) },
                    onSignUpClick = { navController.navigate(Route.SignUpScreen) }
                )
            }

            composable<Route.SignUpScreen> {
                SignUpScreen(
                    onBackClick = { navController.navigateUp()}
                )
            }

            composable<Route.EventListScreen> {
                EventListScreen(
                    onEventClick = { event ->
                        navController.navigate(
                            Route.EventUserScreen(
                                eventTitle = event.title,
                                eventId = event.eventId.toString()
                            )
                        )
                    },
                    navigateToSearchScreen = { navController.navigate(Route.SearchScreen) }
                )
            }

            composable<Route.EventAdminScreen> { EventAdminScreen() }

            composable<Route.EventStepAdminScreen> {  }

            composable<Route.EventUserScreen> {
                val args = it.toRoute<Route.EventUserScreen>()
                EventUserScreen(
                    onBackClick = {navController.navigateUp()},
                    eventTitle = args.eventTitle,
                    eventIdAsString = args.eventId
                )
            }

            composable<Route.EventStepUserScreen> {  }

            composable<Route.SearchScreen> {
                SearchScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
