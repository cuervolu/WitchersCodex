package com.cuervolu.thewitcherscodex.ui.presentation.characters

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.cuervolu.thewitcherscodex.domain.model.Character
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.MediumPadding1
import com.cuervolu.thewitcherscodex.ui.presentation.common.CharactersList
import com.cuervolu.thewitcherscodex.ui.theme.Typography
import kotlinx.coroutines.delay


@Composable
fun CharacterScreen(
    characters: LazyPagingItems<Character>,
    state: CharacterState,
    event: (CharactersEvent) -> Unit,
    navigateToDetails: (Character) -> Unit
) {
    val scrollState = rememberScrollState(initial = state.scrollValue)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {

        Text(
            text = "Characters", modifier = Modifier.padding(horizontal = MediumPadding1),
            fontStyle = Typography.headlineLarge.fontStyle,
            fontWeight = Typography.headlineLarge.fontWeight,
            letterSpacing = Typography.headlineLarge.letterSpacing,
            lineHeight = Typography.headlineLarge.lineHeight,
            color = Typography.headlineLarge.color,
            fontFamily = Typography.headlineLarge.fontFamily,
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        // Update the maxScrollingValue
        LaunchedEffect(key1 = scrollState.maxValue) {
            event(CharactersEvent.UpdateMaxScrollingValue(scrollState.maxValue))
        }
        // Save the state of the scrolling position
        LaunchedEffect(key1 = scrollState.value) {
            event(CharactersEvent.UpdateScrollValue(scrollState.value))
        }
        // Animate the scrolling
        LaunchedEffect(key1 = state.maxScrollingValue) {
            delay(500)
            if (state.maxScrollingValue > 0) {
                scrollState.animateScrollTo(
                    value = state.maxScrollingValue,
                    animationSpec = infiniteRepeatable(
                        tween(
                            durationMillis = (state.maxScrollingValue - state.scrollValue) * 50_000 / state.maxScrollingValue,
                            easing = LinearEasing,
                            delayMillis = 1000
                        )
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(MediumPadding1))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CharactersList(characters = characters, onClick = navigateToDetails)
        }
    }
}