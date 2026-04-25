package com.flopez.feature.notes.presentation.notes.model

import com.flopez.core.presentation.mvi.Contract
import com.flopez.core.presentation.string.UIText
import com.flopez.feature.notes.domain.model.Note

object NotesScreenContract {

    data class State(
        val notes: List<Note> = emptyList(),
        val isLoading: Boolean = false,
        val isCreating: Boolean = false,
        val showCreateDialog: Boolean = false,
        val draftTitle: String = "",
        val draftSubtitle: String = "",
    ) : Contract.State

    sealed interface Intent : Contract.Intent {
        data object Init : Intent
        data object OnAddClick : Intent
        data object OnDismissDialog : Intent
        data class OnDraftTitleChange(val value: String) : Intent
        data class OnDraftSubtitleChange(val value: String) : Intent
        data object OnConfirmCreate : Intent
        data class OnDeleteClick(val id: String) : Intent
    }

    sealed interface Effect : Contract.Effect {
        data class ShowToast(val message: UIText) : Effect
    }
}
