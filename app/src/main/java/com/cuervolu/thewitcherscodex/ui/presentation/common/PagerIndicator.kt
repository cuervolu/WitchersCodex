package com.cuervolu.thewitcherscodex.ui.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.IndicatorSize

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagesSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = Color.Gray
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pagesSize) { page ->
            Box(
                modifier = Modifier
                    .size(IndicatorSize).clip(CircleShape)
                    .background(
                        color = if (page == selectedPage) selectedColor else unselectedColor,
                        shape = CircleShape
                    ))
        }
    }
}