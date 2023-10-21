package com.cuervolu.thewitcherscodex.ui.presentation.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.cuervolu.thewitcherscodex.R
import com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components.Header
import com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components.LoginButton
import com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components.LoginFields
import com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components.SignUpText
import com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components.SocialLogin
import com.cuervolu.thewitcherscodex.ui.theme.darkBlue
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(onEvent: suspend (LoginEvent) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = darkBlue)
            .statusBarsPadding(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        var user by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }
        LoginFields(user = user,
            pass = pass,
            onUserChange = { user = it },
            onPassChange = { pass = it })
        LoginButton {
            coroutineScope.launch {
                onEvent(LoginEvent.OnLoginClick(user, pass))
            }
        }
        SignUpText()
        SocialLogin()
        Image(
            painter = painterResource(id = R.drawable.bottom_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}