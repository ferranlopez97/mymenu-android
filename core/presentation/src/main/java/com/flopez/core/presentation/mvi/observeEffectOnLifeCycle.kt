package com.flopez.core.presentation.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <E : Contract.Effect> ObserveEffectOnLifeCycle(
    effect: Flow<E>,
    onEffect: suspend (E) -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(effect) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            effect.collect { onEffect(it) }
        }
    }
}
