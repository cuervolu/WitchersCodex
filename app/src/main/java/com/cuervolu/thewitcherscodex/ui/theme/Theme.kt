package com.cuervolu.thewitcherscodex.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.cuervolu.thewitcherscodex.R

private val DarkColorScheme = darkColorScheme(
    primary = CTA,
    secondary = Secondary,
    tertiary = CTA
)

private val LightColorScheme = lightColorScheme(
    primary = darkBlue,
    secondary = darkBlue,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(MaterialTheme.colorScheme.primary.value),
            contentColor = Color(MaterialTheme.colorScheme.secondary.value)
        ),
    ) {
        Text(text = text, fontSize = 12.sp)
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color(R.color.secondary),
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, Color(R.color.accent))
    ) {
        Text(text = text, fontSize = 12.sp)
    }
}

@Composable
fun AccentButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color(R.color.accent),
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, Color(R.color.accent))
    ) {
        Text(text = text, fontSize = 12.sp, color = Color(R.color.accent))
    }
}

@Composable
fun TertiaryButton(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color(R.color.secondary),
        modifier = Modifier
            .clickable { onClick() }
            .padding(top = 16.dp)
    )
}

@Composable
fun FormEditText(
    text: String,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        value = text,
        onValueChange = { newText -> onTextChange(newText) },
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = Color(R.color.secondary_light),
            fontFamily = FontFamily(fonts = listOf(Font(R.font.montserrat)))
        ),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .background(Color(R.color.primary))
            .border(
                width = 1.dp,
                color = Color(R.color.secondary)
            )
    )
}

@Composable
fun FormTextInputLayout(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        content()
    }
}

@Composable
fun TheWitchersCodexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
