package com.cuervolu.thewitcherscodex

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuervolu.thewitcherscodex.domain.usecases.app_entry.AppEntryUseCases
import com.cuervolu.thewitcherscodex.ui.presentation.navgraph.Route
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: MutableStateFlow<Boolean> = _isUserLoggedIn

    init {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            _isUserLoggedIn.value = true
        }

        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen && _isUserLoggedIn.value) {
                _startDestination.value = Route.WitcherNavigation.route
            } else {
                _startDestination.value = Route.AppStartNavigation.route
            }
            delay(200) //Without this delay, the onBoarding screen will show for a momentum.
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}