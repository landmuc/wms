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
import com.landmuc.authentication.SignInScreen
import com.landmuc.authentication.SignUpScreen

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
                    onSignInClick = { navController.navigate(Route.EventListScreen) },
                    onSignUpClick = { navController.navigate(Route.SignUpScreen) }
                )
            }

            composable<Route.SignUpScreen> {
                SignUpScreen(
                    onBackClick = { navController.navigate(Route.SignInScreen)}
                )
            }

            composable<Route.EventListScreen> {  }

            composable<Route.EventAdminScreen> {  }

            composable<Route.EventStepAdminScreen> {  }

            composable<Route.EventUserScreen> {  }

            composable<Route.EventStepUserScreen> {  }
        }
    }
}
