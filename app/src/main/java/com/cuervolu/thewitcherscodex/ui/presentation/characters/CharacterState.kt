package com.cuervolu.thewitcherscodex.ui.presentation.characters

data class CharacterState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
    val scrollValue: Int = 0,
    val maxScrollingValue: Int = 0
)