package com.flopez.feature.home.presentation.home.model

import com.flopez.core.presentation.mvi.Contract
import com.flopez.core.presentation.string.UIText

object HomeScreenContract {

    data class State(
        val isLoggingOut: Boolean = false,
        val userEmail: String? = null,
    ) : Contract.State

    sealed interface Intent : Contract.Intent {
        data object OnLogoutClick : Intent
    }

    sealed interface Effect : Contract.Effect {
        data class ShowToast(val message: UIText) : Effect
    }
}
