package com.flopez.feature.authentication.presentation.login.model

import com.flopez.core.presentation.mvi.Contract

object LoginScreenContract {

    data class State(
        val isLoading: Boolean = false,
        val username: String = "",
        val password: String = "",
        val isRegisterMode: Boolean = false,
    ) : Contract.State

    sealed interface Intent : Contract.Intent {
        data class OnUserNameChange(val newValue: String) : Intent
        data class OnPasswordChange(val newValue: String) : Intent
        data object OnLoginClick : Intent
        data object OnRegisterModeToggle : Intent
    }

    sealed interface Effect : Contract.Effect {
        data class ShowToast(val message: String) : Effect
    }
}