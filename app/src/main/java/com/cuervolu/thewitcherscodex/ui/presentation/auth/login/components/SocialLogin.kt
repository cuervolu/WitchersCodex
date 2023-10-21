package com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cuervolu.thewitcherscodex.R

@Composable
fun SocialLogin() {
    Row(Modifier.padding(top = 16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = "Google", Modifier.padding(top = 16.dp)
        )
    }
}