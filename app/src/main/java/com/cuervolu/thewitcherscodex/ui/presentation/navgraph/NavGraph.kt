package com.cuervolu.thewitcherscodex.ui.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
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
                OnBoardingScreen(onEvent = viewModel::onEvent)
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