package com.cuervolu.thewitcherscodex.ui.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cuervolu.thewitcherscodex.domain.usecases.streams.StreamUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val streamUseCases: StreamUseCases) : ViewModel() {
    var state = mutableStateOf(HomeState())
        private set
    val stream = streamUseCases.getStreams().cachedIn(viewModelScope)

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpdateScrollValue -> updateScrollValue(event.newValue)
            is HomeEvent.UpdateMaxScrollingValue -> updateMaxScrollingValue(event.newValue)
        }
    }

    private fun updateScrollValue(newValue: Int) {
        state.value = state.value.copy(scrollValue = newValue)
    }

    private fun updateMaxScrollingValue(newValue: Int) {
        state.value = state.value.copy(maxScrollingValue = newValue)
    }

}