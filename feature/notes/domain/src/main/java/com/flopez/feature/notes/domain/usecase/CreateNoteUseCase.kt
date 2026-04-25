package com.flopez.feature.notes.domain.usecase

import com.flopez.core.domain.usecase.base.UseCase
import com.flopez.core.domain.usecase.base.UseCaseResult
import com.flopez.feature.notes.domain.repository.NotesRepository

class CreateNoteUseCase(
    private val repository: NotesRepository,
) : UseCase<CreateNoteUseCase.Params, Unit>() {

    override suspend fun run(params: Params): UseCaseResult<Unit> {
        repository.createNote(params.title, params.subtitle)
        return UseCaseResult.Success(Unit)
    }

    data class Params(
        val title: String,
        val subtitle: String?,
    )
}
