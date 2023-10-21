package com.cuervolu.thewitcherscodex.ui.presentation.auth.login

sealed class LoginEvent {
    data class OnLoginClick(val username: String, val password: String) : LoginEvent()
}
