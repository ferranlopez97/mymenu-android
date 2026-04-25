package com.flopez.feature.notes.domain.usecase

import com.flopez.core.domain.usecase.base.NoParamsUseCase
import com.flopez.core.domain.usecase.base.UseCaseResult
import com.flopez.feature.notes.domain.model.Note
import com.flopez.feature.notes.domain.repository.NotesRepository

class GetNotesUseCase(
    private val repository: NotesRepository,
) : NoParamsUseCase<List<Note>>() {

    override suspend fun run(): UseCaseResult<List<Note>> =
        UseCaseResult.Success(repository.getNotes())
}
