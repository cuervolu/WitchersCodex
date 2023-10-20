package com.cuervolu.thewitcherscodex.ui.presentation.navigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.cuervolu.thewitcherscodex.R
import com.cuervolu.thewitcherscodex.domain.model.Character
import com.cuervolu.thewitcherscodex.ui.presentation.characters.CharacterScreen
import com.cuervolu.thewitcherscodex.ui.presentation.characters.components.CharactersViewModel
import com.cuervolu.thewitcherscodex.ui.presentation.home.HomeScreen
import com.cuervolu.thewitcherscodex.ui.presentation.home.HomeViewModel
import com.cuervolu.thewitcherscodex.ui.presentation.navgraph.Route
import com.cuervolu.thewitcherscodex.ui.presentation.navigator.components.BottomNavigation
import com.cuervolu.thewitcherscodex.ui.presentation.navigator.components.BottomNavigationItem

@Composable
fun AppNavigator() {
    // Define bottom navigation items
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.guide, text = "Fun Facts"),
            BottomNavigationItem(icon = R.drawable.home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.task, text = "Profile"),
        )
    }

    // Create a navigation controller
    val navController = rememberNavController()

    // Determine the selected item based on the current route
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    selectedItem = when (backStackState?.destination?.route) {
        Route.CharacterScreen.route -> 0
        Route.HomeScreen.route -> 1
        Route.ProfileScreen.route -> 2
        else -> 0
    }

    // Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.CharacterScreen.route ||
                backStackState?.destination?.route == Route.ProfileScreen.route
    }

    // Scaffold with bottom navigation
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            BottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(navController, Route.CharacterScreen.route)
                        1 -> navigateToTab(navController, Route.HomeScreen.route)
                        2 -> navigateToTab(navController, Route.ProfileScreen.route)
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()

        // Define the navigation host for different screens
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                // Obtain the HomeViewModel using Hilt
                val viewModel: HomeViewModel = hiltViewModel()
                // Collect the stream data as LazyPagingItems
                val streams = viewModel.stream.collectAsLazyPagingItems()
                HomeScreen(
                    streams = streams,
                    navigateToSearch = {
                        navigateToTab(navController, Route.CharacterScreen.route)
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(navController, article)
                    },
                    event = viewModel::onEvent,
                    state = viewModel.state.value
                )
            }
            composable(route = Route.CharacterScreen.route) {
                val viewModel = hiltViewModel<CharactersViewModel>()
                val characters = viewModel.characters.collectAsLazyPagingItems()
                OnBackClickStateSaver(navController = navController)
                CharacterScreen(
                    characters = characters,
                    state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigateToDetails = { character ->
                        navigateToDetails(navController, character)
                    }
                )

            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    // Handle back button clicks and navigate to the HomeScreen
    BackHandler(true) {
        navigateToTab(navController, Route.HomeScreen.route)
    }
}

// Function to navigate to a specific tab in the bottom navigation
private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

// Function to navigate to the details screen
private inline fun <reified T> navigateToDetails(navController: NavController, item: T) {
    val route = when (T::class) {
        Character::class -> Route.CharacterDetailScreen.route
        //OtraClaseDeDetalle::class -> Route.OtraClaseDeDetalleDetailScreen.route
        // Agrega más casos para otros tipos de detalle si es necesario
        else -> throw IllegalArgumentException("Tipo de detalle no compatible: ${T::class.simpleName}")
    }

    navController.currentBackStackEntry?.savedStateHandle?.set("item", item)
    navController.navigate(route)
}

