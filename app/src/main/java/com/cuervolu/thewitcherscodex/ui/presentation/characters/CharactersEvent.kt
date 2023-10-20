package com.cuervolu.thewitcherscodex.ui.presentation.characters


sealed class CharactersEvent {
    data class UpdateScrollValue(val newValue: Int) : CharactersEvent()
    data class UpdateMaxScrollingValue(val newValue: Int) : CharactersEvent()
}