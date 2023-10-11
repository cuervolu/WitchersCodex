package com.cuervolu.thewitcherscodex.ui.presentation.onboarding

import androidx.annotation.DrawableRes
import com.cuervolu.thewitcherscodex.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Welcome to The Witcher's Codex",
        description = "Explore the fascinating world of The Witcher 3 with fun facts, streams, and more.",
        image = R.drawable.witcher_image1
    ),
    Page(
        title = "Discover Fun Facts",
        description = "Learn interesting tidbits and facts about The Witcher 3 universe.",
        image = R.drawable.witcher_image2
    ),
    Page(
        title = "Live Streams and More",
        description = "Stay updated with live streams, tips, and exciting content from The Witcher's world.",
        image = R.drawable.witcher_image3
    )
)






