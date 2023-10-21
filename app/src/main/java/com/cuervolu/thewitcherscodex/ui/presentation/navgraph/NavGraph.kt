package com.cuervolu.thewitcherscodex.ui.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.cuervolu.thewitcherscodex.ui.presentation.auth.login.LoginScreen
import com.cuervolu.thewitcherscodex.ui.presentation.auth.login.LoginViewModel
import com.cuervolu.thewitcherscodex.ui.presentation.navigator.AppNavigator
import com.cuervolu.thewitcherscodex.ui.presentation.onboarding.OnBoardingScreen
import com.cuervolu.thewitcherscodex.ui.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen { event ->
                    viewModel.onEvent(event, navController)
                }
            }
            composable(route = Route.LoginScreen.route) {
                val viewModel: LoginViewModel = hiltViewModel()
                LoginScreen { event ->
                    viewModel.onEvent(event, navController)
                }
            }
        }

        navigation(
            route = Route.WitcherNavigation.route,
            startDestination = Route.WitcherNavigatorScreen.route
        ) {
            composable(route = Route.WitcherNavigatorScreen.route) {
                AppNavigator()
            }
        }
    }
}