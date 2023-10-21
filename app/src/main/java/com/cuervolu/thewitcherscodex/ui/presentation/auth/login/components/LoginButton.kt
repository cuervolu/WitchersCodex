package com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cuervolu.thewitcherscodex.ui.presentation.auth.login.LoginEvent
import com.cuervolu.thewitcherscodex.ui.theme.CTA

@Composable
fun LoginButton(onLoginClick: (LoginEvent.OnLoginClick) -> Unit) {
    Button(
        onClick = { onLoginClick(LoginEvent.OnLoginClick("", "")) },
        Modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(start = 64.dp, end = 64.dp, top = 8.dp, bottom = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = CTA),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = "Login",
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
    }
}
