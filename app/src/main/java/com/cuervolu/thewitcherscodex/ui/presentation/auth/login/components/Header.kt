package com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cuervolu.thewitcherscodex.R

@Composable
fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.top_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.height(150.dp)
        )
        Text(
            text = "Welcome to The Witcher's Codex",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
    }
}