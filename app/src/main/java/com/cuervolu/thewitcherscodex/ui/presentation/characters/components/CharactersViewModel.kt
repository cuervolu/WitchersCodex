package com.cuervolu.thewitcherscodex.ui.presentation.characters.components

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cuervolu.thewitcherscodex.domain.usecases.characters.CharacterUseCases
import com.cuervolu.thewitcherscodex.ui.presentation.characters.CharacterState
import com.cuervolu.thewitcherscodex.ui.presentation.characters.CharactersEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(characterUseCases: CharacterUseCases) :
    ViewModel() {
    var state = mutableStateOf(CharacterState())
        private set
    val characters = characterUseCases.getCharacters().cachedIn(viewModelScope)

    fun onEvent(event: CharactersEvent) {
        when (event) {
            is CharactersEvent.UpdateScrollValue -> updateScrollValue(event.newValue)
            is CharactersEvent.UpdateMaxScrollingValue -> updateMaxScrollingValue(event.newValue)
        }
    }

    private fun updateScrollValue(newValue: Int) {
        state.value = state.value.copy(scrollValue = newValue)
    }

    private fun updateMaxScrollingValue(newValue: Int) {
        state.value = state.value.copy(maxScrollingValue = newValue)
    }
}