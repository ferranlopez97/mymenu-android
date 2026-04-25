package com.flopez.feature.notes.presentation.notes

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flopez.core.presentation.mvi.ObserveEffectOnLifeCycle
import com.flopez.core.presentation.theme.VerdantPantryTheme
import com.flopez.core.presentation.theme.VerdantTheme
import com.flopez.feature.notes.domain.model.Note
import com.flopez.feature.notes.presentation.R
import com.flopez.feature.notes.presentation.notes.model.NotesScreenContract
import com.flopez.feature.notes.presentation.notes.model.NotesScreenContract.Effect
import com.flopez.feature.notes.presentation.notes.model.NotesScreenContract.Intent
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel(),
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) { viewModel.onIntent(Intent.Init) }

    ObserveEffectOnLifeCycle(effect = viewModel.effect) { effect ->
        when (effect) {
            is Effect.ShowToast -> Toast.makeText(
                context,
                effect.message.asString(context),
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

    NotesContent(uiState = uiState, onIntent = viewModel::onIntent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotesContent(
    uiState: NotesScreenContract.State,
    onIntent: (Intent) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { onIntent(Intent.OnAddClick) }) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.notes_add))
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            when {
                uiState.isLoading && uiState.notes.isEmpty() ->
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                uiState.notes.isEmpty() ->
                    Text(
                        text = stringResource(R.string.notes_empty),
                        style = VerdantTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center),
                    )
                else -> NotesList(
                    notes = uiState.notes,
                    onDelete = { onIntent(Intent.OnDeleteClick(it)) },
                )
            }
        }
    }

    if (uiState.showCreateDialog) {
        ModalBottomSheet(
            onDismissRequest = { onIntent(Intent.OnDismissDialog) },
            sheetState = sheetState,
        ) {
            CreateNoteSheetContent(
                title = uiState.draftTitle,
                subtitle = uiState.draftSubtitle,
                isCreating = uiState.isCreating,
                onTitleChange = { onIntent(Intent.OnDraftTitleChange(it)) },
                onSubtitleChange = { onIntent(Intent.OnDraftSubtitleChange(it)) },
                onConfirm = { onIntent(Intent.OnConfirmCreate) },
                onDismiss = { onIntent(Intent.OnDismissDialog) },
            )
        }
    }
}

@Composable
private fun NotesList(
    notes: List<Note>,
    onDelete: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 16.dp),
    ) {
        items(notes, key = { it.id }) { note ->
            NoteRow(note = note, onDelete = { onDelete(note.id) })
        }
    }
}

@Composable
private fun NoteRow(note: Note, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = note.title, style = VerdantTheme.typography.titleMedium)
                if (!note.subtitle.isNullOrBlank()) {
                    Spacer(Modifier.height(4.dp))
                    Text(text = note.subtitle!!, style = VerdantTheme.typography.bodyMedium)
                }
                Spacer(Modifier.height(4.dp))
                Text(text = note.createdAt, style = VerdantTheme.typography.bodySmall)
            }
            IconButton(
                onClick = onDelete,
                modifier = Modifier.align(Alignment.TopEnd),
            ) {
                Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.notes_delete))
            }
        }
    }
}

@Composable
private fun CreateNoteSheetContent(
    title: String,
    subtitle: String,
    isCreating: Boolean,
    onTitleChange: (String) -> Unit,
    onSubtitleChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
            .padding(bottom = 24.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.notes_dialog_title),
                style = VerdantTheme.typography.titleMedium,
            )
        }
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text(stringResource(R.string.notes_field_title)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = subtitle,
            onValueChange = onSubtitleChange,
            label = { Text(stringResource(R.string.notes_field_subtitle)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onConfirm,
            enabled = !isCreating && title.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.notes_save))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesPreview() {
    VerdantPantryTheme {
        NotesContent(
            uiState = NotesScreenContract.State(
                notes = listOf(
                    Note("1", "Comprar pan", "Antes de las 8", "2026-04-24T10:00:00Z"),
                ),
            ),
            onIntent = {},
        )
    }
}
