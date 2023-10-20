package com.cuervolu.thewitcherscodex.ui.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import com.cuervolu.thewitcherscodex.domain.model.StreamsData
import com.cuervolu.thewitcherscodex.homestreamcard.Design
import com.cuervolu.thewitcherscodex.homestreamcard.HomeStreamCard
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.ExtraSmallPadding
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.StreamCardHeight
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.StreamCardWidth
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.StreamImageHeight
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.StreamImageWidth

@Composable
fun StreamCard(modifier: Modifier = Modifier, stream: StreamsData, onClick: (() -> Unit)? = null) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() },
        horizontalArrangement = Arrangement.spacedBy(
            ExtraSmallPadding
        )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround, modifier = modifier
                .width(
                    StreamCardWidth
                )
                .height(
                    StreamCardHeight
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val thumbnailURL =
                generateThumbnailURL(stream.thumbnail_url, StreamImageWidth, StreamImageHeight)
            HomeStreamCard(
                design = Design.Small,
                thumbnail = rememberAsyncImagePainter(thumbnailURL),
                user = stream.user_name,
                viewCount = stream.viewer_count.toString() + " watching",
                title = stream.title,
                ivUser = rememberAsyncImagePainter(thumbnailURL)
            )

        }

    }
}

private fun generateThumbnailURL(originalURL: String, width: Int, height: Int): String {
    return originalURL.replace("{width}", width.toString()).replace("{height}", height.toString())
}