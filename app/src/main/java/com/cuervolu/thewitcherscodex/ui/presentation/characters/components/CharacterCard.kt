package com.cuervolu.thewitcherscodex.ui.presentation.characters.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cuervolu.thewitcherscodex.domain.model.Character

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: (() -> Unit)? = null
) {

    Card(modifier = modifier
        .clickable { onClick?.invoke() }
        .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = rememberAsyncImagePainter(character.image_url),
                contentDescription = character.name,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp), contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = character.name,
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }
        }
    }
}


