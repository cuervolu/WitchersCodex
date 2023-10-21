package com.cuervolu.thewitcherscodex.ui.presentation.auth.login.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cuervolu.thewitcherscodex.ui.theme.Pink40

@Composable
fun LoginFields(
    user: String,
    pass: String,
    onUserChange: (String) -> Unit,
    onPassChange: (String) -> Unit
) {
    val passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    TextField(
        value = user,
        onValueChange = { text -> onUserChange(text) },
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(start = 64.dp, end = 64.dp, top = 8.dp, bottom = 8.dp)
            .border(1.dp, Pink40, shape = RoundedCornerShape(50)),
        shape = RoundedCornerShape(50),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 14.sp
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        placeholder = { Text("Email") }
    )

    TextField(
        value = pass,
        onValueChange = { text -> onPassChange(text) },
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(start = 64.dp, end = 64.dp, top = 8.dp, bottom = 8.dp)
            .border(1.dp, Pink40, shape = RoundedCornerShape(50)),
        shape = RoundedCornerShape(50),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 14.sp
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        visualTransformation = if (passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = { Text("Password") }
    )

}