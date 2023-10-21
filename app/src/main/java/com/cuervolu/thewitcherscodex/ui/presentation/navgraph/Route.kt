package com.cuervolu.thewitcherscodex.ui.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object LoginScreen : Route(route = "loginScreen")
    object HomeScreen : Route(route = "homeScreen")
    object CharacterScreen : Route(route = "characterScreen")
    object CharacterDetailScreen : Route(route = "characterDetailScreen")
    object SearchScreen : Route(route = "searchScreen")
    object ProfileScreen : Route(route = "profileScreen")

    object BestiaryScreen : Route(route = "bestiaryScreen")

    object BestiaryDetailScreen : Route(route = "bestiaryDetailScreen")

    object StreamScreen : Route(route = "streamScreen")

    object StreamDetailScreen : Route(route = "streamDetailScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object DashboardScreen : Route(route = "dashboardScreen")

    object WitcherNavigation : Route(route = "witcherNavigation")

    object WitcherNavigatorScreen : Route(route = "witcherNavigator")

}