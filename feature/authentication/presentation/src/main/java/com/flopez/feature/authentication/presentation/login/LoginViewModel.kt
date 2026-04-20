package com.flopez.feature.authentication.presentation.login

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.flopez.core.domain.usecase.base.finally
import com.flopez.core.domain.usecase.base.onError
import com.flopez.core.domain.usecase.base.onSuccess
import com.flopez.core.presentation.mvi.BaseViewModel
import com.flopez.core.presentation.string.UIText
import com.flopez.feature.authentication.domain.usecase.LoginUseCase
import com.flopez.feature.authentication.domain.usecase.RegisterUseCase
import com.flopez.feature.authentication.presentation.R
import com.flopez.feature.authentication.presentation.login.model.LoginScreenContract.Effect
import com.flopez.feature.authentication.presentation.login.model.LoginScreenContract.Intent
import com.flopez.feature.authentication.presentation.login.model.LoginScreenContract.State
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel<State, Intent, Effect>(State()) {

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.OnUserNameChange     -> onUsernameChanged(intent.newValue)
            is Intent.OnPasswordChange     -> onPasswordChanged(intent.newValue)
            is Intent.OnLoginClick         -> onCTAClicked()
            is Intent.OnRegisterModeToggle -> onRegisterModeToggled()
        }
    }

    private fun onUsernameChanged(value: String) {
        updateState { copy(username = value) }
    }

    private fun onPasswordChanged(value: String) {
        updateState { copy(password = value) }
    }

    private fun onRegisterModeToggled() {
        updateState { copy(isRegisterMode = !isRegisterMode) }
    }

    private fun onCTAClicked() {
        if (state.value.isLoading) return

        if (state.value.isRegisterMode) onRegisterClicked() else onLoginClicked()
    }

    private fun onLoginClicked() {
        updateState { copy(isLoading = true) }

        viewModelScope.launch {
            loginUseCase.execute(
                LoginUseCase.Params(
                    username = state.value.username,
                    password = state.value.password,
                )
            ).onSuccess {
                sendToastEffect(R.string.login_toast_login_success)
            }.onError {
                sendToastEffect(R.string.login_toast_login_error)
            }.finally {
                updateState { copy(isLoading = false) }
            }
        }
    }

    private fun onRegisterClicked() {
        if (state.value.isLoading) return
        updateState { copy(isLoading = true) }

        viewModelScope.launch {
            registerUseCase.execute(
                RegisterUseCase.Params(
                    username = state.value.username,
                    password = state.value.password,
                )
            ).onSuccess {
                sendToastEffect(R.string.login_toast_register_success)
            }.onError {
                sendToastEffect(R.string.login_toast_register_error)
            }.finally {
                updateState { copy(isLoading = false) }
            }
        }
    }

    private fun sendToastEffect(@StringRes res: Int) {
        sendEffect(Effect.ShowToast(UIText.StringResource(R.string.login_toast_register_error)))
    }
}
