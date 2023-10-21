package com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpText() {
    Text(
        text = "Don't have an account? Sign up",
        fontSize = 14.sp,
        color = Color.White,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    )
}