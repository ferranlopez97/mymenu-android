package com.flopez.feature.notes.presentation.notes

import androidx.lifecycle.viewModelScope
import com.flopez.core.domain.usecase.base.onError
import com.flopez.core.domain.usecase.base.onSuccess
import com.flopez.core.presentation.mvi.BaseViewModel
import com.flopez.core.presentation.string.UIText
import com.flopez.feature.notes.domain.usecase.CreateNoteUseCase
import com.flopez.feature.notes.domain.usecase.DeleteNoteUseCase
import com.flopez.feature.notes.domain.usecase.GetNotesUseCase
import com.flopez.feature.notes.presentation.R
import com.flopez.feature.notes.presentation.notes.model.NotesScreenContract.Effect
import com.flopez.feature.notes.presentation.notes.model.NotesScreenContract.Intent
import com.flopez.feature.notes.presentation.notes.model.NotesScreenContract.State
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val createNoteUseCase: CreateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : BaseViewModel<State, Intent, Effect>(State()) {

    override fun onIntent(intent: Intent) {
        when (intent) {
            Intent.Init -> loadNotes()
            Intent.OnAddClick -> updateState {
                copy(showCreateDialog = true, draftTitle = "", draftSubtitle = "")
            }
            Intent.OnDismissDialog -> updateState { copy(showCreateDialog = false) }
            is Intent.OnDraftTitleChange -> updateState { copy(draftTitle = intent.value) }
            is Intent.OnDraftSubtitleChange -> updateState { copy(draftSubtitle = intent.value) }
            Intent.OnConfirmCreate -> createNote()
            is Intent.OnDeleteClick -> deleteNote(intent.id)
        }
    }

    private fun loadNotes() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getNotesUseCase.execute()
                .onSuccess { notes -> updateState { copy(notes = notes, isLoading = false) } }
                .onError {
                    updateState { copy(isLoading = false) }
                    sendEffect(Effect.ShowToast(UIText.StringResource(R.string.notes_load_error)))
                }
        }
    }

    private fun createNote() {
        val title = state.value.draftTitle.trim()
        if (title.isEmpty() || state.value.isCreating) return
        val subtitle = state.value.draftSubtitle.trim().ifEmpty { null }

        updateState { copy(isCreating = true) }
        viewModelScope.launch {
            createNoteUseCase.execute(CreateNoteUseCase.Params(title, subtitle))
                .onSuccess {
                    updateState { copy(isCreating = false, showCreateDialog = false) }
                    loadNotes()
                }
                .onError {
                    updateState { copy(isCreating = false) }
                    sendEffect(Effect.ShowToast(UIText.StringResource(R.string.notes_create_error)))
                }
        }
    }

    private fun deleteNote(id: String) {
        viewModelScope.launch {
            deleteNoteUseCase.execute(DeleteNoteUseCase.Params(id))
                .onSuccess { loadNotes() }
                .onError {
                    sendEffect(Effect.ShowToast(UIText.StringResource(R.string.notes_delete_error)))
                }
        }
    }
}
