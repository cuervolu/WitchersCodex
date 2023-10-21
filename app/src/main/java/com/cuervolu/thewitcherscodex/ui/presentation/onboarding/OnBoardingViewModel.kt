package com.cuervolu.thewitcherscodex.ui.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.cuervolu.thewitcherscodex.domain.usecases.app_entry.AppEntryUseCases
import com.cuervolu.thewitcherscodex.ui.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    fun onEvent(event: OnBoardingEvent, navController: NavController) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveUserEntry(navController)
            }
        }
    }

    private fun saveUserEntry(navController: NavController) {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
            navController.navigate(Route.LoginScreen.route)
        }
    }

}