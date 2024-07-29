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
import com.landmuc.authentication.sign_in.SignInScreen
import com.landmuc.authentication.sign_up.SignUpScreen
import com.landmuc.event_admin.EventAdminScreen
import com.landmuc.event_list.EventListScreen
import com.landmuc.event_user.EventUserScreen

@Composable
fun RootNavigation() {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.SignInScreen,
            modifier = Modifier.padding(it)
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
                    onBackClick = { navController.navigate(Route.SignInScreen)}
                )
            }

            composable<Route.EventListScreen> { EventListScreen() }

            composable<Route.EventAdminScreen> { EventAdminScreen() }

            composable<Route.EventStepAdminScreen> {  }

            composable<Route.EventUserScreen> { EventUserScreen() }

            composable<Route.EventStepUserScreen> {  }
        }
    }
}
