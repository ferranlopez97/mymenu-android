package com.flopez.feature.home.presentation.home

import androidx.lifecycle.viewModelScope
import com.flopez.core.domain.usecase.base.finally
import com.flopez.core.domain.usecase.base.onError
import com.flopez.core.presentation.mvi.BaseViewModel
import com.flopez.core.presentation.string.UIText
import com.flopez.feature.authentication.domain.usecase.LogoutUseCase
import com.flopez.feature.authentication.domain.usecase.ObserveCurrentUserEmailUseCase
import com.flopez.feature.home.presentation.R
import com.flopez.feature.home.presentation.home.model.HomeScreenContract.Effect
import com.flopez.feature.home.presentation.home.model.HomeScreenContract.Intent
import com.flopez.feature.home.presentation.home.model.HomeScreenContract.State
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logoutUseCase: LogoutUseCase,
    observeCurrentUserEmail: ObserveCurrentUserEmailUseCase,
) : BaseViewModel<State, Intent, Effect>(State()) {

    init {
        viewModelScope.launch {
            observeCurrentUserEmail().collect { email ->
                updateState { copy(userEmail = email) }
            }
        }
    }

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.OnLogoutClick -> onLogoutClicked()
        }
    }

    private fun onLogoutClicked() {
        if (state.value.isLoggingOut) return
        updateState { copy(isLoggingOut = true) }

        viewModelScope.launch {
            logoutUseCase.execute()
                .onError {
                    sendEffect(Effect.ShowToast(UIText.StringResource(R.string.home_logout_error)))
                }
                .finally {
                    updateState { copy(isLoggingOut = false) }
                }
        }
    }
}
