package com.flopez.mymenu.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flopez.feature.authentication.domain.usecase.ObserveSessionUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RootViewModel(
    observeSession: ObserveSessionUseCase,
) : ViewModel() {

    val sessionState: StateFlow<SessionState> = observeSession()
        .map { if (it) SessionState.LoggedIn else SessionState.LoggedOut }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = SessionState.Loading,
        )

    sealed interface SessionState {
        data object Loading : SessionState
        data object LoggedIn : SessionState
        data object LoggedOut : SessionState
    }
}
