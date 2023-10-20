package com.cuervolu.thewitcherscodex.ui.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import com.cuervolu.thewitcherscodex.R
import com.cuervolu.thewitcherscodex.homestreamcardshimmer.HomeStreamCardShimmer
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.StreamCardHeight
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.StreamCardWidth

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "infiniteTransition")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = "Shimmer Effect"
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun StreamCardShimmerEffect(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
        contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding2)
    ) {
        repeat(5) { // Adjust the repeat count as needed
            item {
                HomeStreamCardShimmer(
                    modifier = Modifier
                        .shimmerEffect()
                        .width(StreamCardWidth)
                        .height(StreamCardHeight)
                )
            }
        }
    }
}

@Composable
fun CharacterShimmerEffect(modifier: Modifier = Modifier, shape: Shape = RoundedCornerShape(15)) {
    Box(
        modifier = modifier.background(Color(0xFFD3D3D3), shape),
    ) {
        val gradientWidth = 800 // Ancho del gradiente
        val gradientHeight = 400 // Alto del gradiente

        val brush = Brush.horizontalGradient(
            colors = listOf(
                Color(0xFFD3D3D3), // Color base
                Color(0xFFE2E2E2), // Color más claro
                Color(0xFFD3D3D3)  // Color base nuevamente
            ),
            startX = 0f,
            endX = gradientWidth.toFloat()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brush)
                .graphicsLayer(
                    translationX = animateFloatAsState(
                        targetValue = gradientWidth.toFloat(),
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000), // Velocidad del efecto
                            repeatMode = RepeatMode.Reverse
                        ), label = ""
                    ).value
                )
        )
    }
}
