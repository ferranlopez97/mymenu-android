package com.flopez.feature.notes.domain.usecase

import com.flopez.core.domain.usecase.base.UseCase
import com.flopez.core.domain.usecase.base.UseCaseResult
import com.flopez.feature.notes.domain.repository.NotesRepository

class DeleteNoteUseCase(
    private val repository: NotesRepository,
) : UseCase<DeleteNoteUseCase.Params, Unit>() {

    override suspend fun run(params: Params): UseCaseResult<Unit> {
        repository.deleteNote(params.id)
        return UseCaseResult.Success(Unit)
    }

    data class Params(val id: String)
}
