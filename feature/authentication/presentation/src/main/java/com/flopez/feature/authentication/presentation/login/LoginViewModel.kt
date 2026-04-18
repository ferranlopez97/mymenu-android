package com.flopez.feature.authentication.presentation.login

import androidx.lifecycle.viewModelScope
import com.flopez.core.domain.usecase.base.finally
import com.flopez.core.domain.usecase.base.onError
import com.flopez.core.domain.usecase.base.onSuccess
import com.flopez.core.presentation.mvi.BaseViewModel
import com.flopez.feature.authentication.domain.usecase.LoginUseCase
import com.flopez.feature.authentication.presentation.login.model.LoginScreenContract.Effect
import com.flopez.feature.authentication.presentation.login.model.LoginScreenContract.Intent
import com.flopez.feature.authentication.presentation.login.model.LoginScreenContract.State
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<State, Intent, Effect>(State(isLoading = false)) {

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.OnUserNameChange -> onUsernameChanged(intent.newValue)
            is Intent.OnPasswordChange -> onPasswordChanged(intent.newValue)
            is Intent.OnLoginClick -> onLoginClicked()
        }
    }

    private fun onUsernameChanged(value: String) {
        updateState {
            copy(
                isLoading = false,
                username = value
            )
        }
    }

    private fun onPasswordChanged(value: String) {
        updateState {
            copy(
                isLoading = false,
                password = value
            )
        }
    }

    private fun onLoginClicked() {
        if (state.value.isLoading) return

        updateState {
            copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            loginUseCase.execute(
                LoginUseCase.Params(
                    username = state.value.username,
                    password = state.value.password,
                )
            ).onSuccess {
                sendEffect(
                    effect = Effect.ShowToast("Success")
                )
            }.onError {
                sendEffect(
                    effect = Effect.ShowToast("Error")
                )
            }.finally {
                updateState {
                    copy(isLoading = false)
                }
            }
        }
    }


}
