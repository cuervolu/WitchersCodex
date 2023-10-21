package com.cuervolu.thewitcherscodex.ui.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.cuervolu.thewitcherscodex.domain.usecases.auth.AuthUseCases
import com.cuervolu.thewitcherscodex.ui.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: AuthUseCases
) : ViewModel() {

    suspend fun onEvent(event: LoginEvent, navController: NavController) {
        when (event) {
            is LoginEvent.OnLoginClick -> {
                val isLoginSuccessful = loginUseCase.login(event.username, event.password)
                if (isLoginSuccessful) {

                    navController.navigate(Route.WitcherNavigation.route)
                }
            }
        }
    }
}
