package com.flopez.core.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Contract.State, Intent : Contract.Intent, Effect : Contract.Effect>(
    initialState: State,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect = Channel<Effect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    abstract fun onIntent(intent: Intent)

    protected fun updateState(reducer: State.() -> State) {
        _state.update { it.reducer() }
    }

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch(Dispatchers.Main) { _effect.send(effect) }
    }
}
