package com.cuervolu.thewitcherscodex.ui.presentation.navigator.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cuervolu.thewitcherscodex.R
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.ExtraSmallPadding2
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.IconSize
import com.cuervolu.thewitcherscodex.ui.theme.TheWitchersCodexTheme
import com.cuervolu.thewitcherscodex.ui.theme.darkBlue

@Composable
fun BottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF000000), Color(0xFF131E25)),
        startY = 0f,
        endY = 100f
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = gradientBrush)
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = darkBlue,
            tonalElevation = 0.dp
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == selectedItem,
                    onClick = { onItemClick(index) },
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = null,
                                modifier = Modifier.size(IconSize),
                            )
                            Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = Color.White,
                        unselectedTextColor = colorResource(id = R.color.body),
                        indicatorColor = MaterialTheme.colorScheme.background,
                    ),
                )
            }
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    TheWitchersCodexTheme(dynamicColor = false) {
        BottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.guide, text = "Dashboard"),
            BottomNavigationItem(icon = R.drawable.home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.task, text = "Profile"),
        ), selectedItem = 0, onItemClick = {})
    }
}