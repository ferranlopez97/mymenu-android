package com.flopez.mymenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.flopez.core.presentation.theme.VerdantPantryTheme
import com.flopez.feature.authentication.presentation.login.LoginScreen
import com.flopez.feature.home.presentation.home.HomeScreen
import com.flopez.feature.notes.presentation.notes.NotesScreen
import com.flopez.mymenu.navigation.HomeKey
import com.flopez.mymenu.navigation.LoginKey
import com.flopez.mymenu.navigation.NotesKey
import com.flopez.mymenu.root.RootViewModel
import com.flopez.mymenu.root.RootViewModel.SessionState
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VerdantPantryTheme {
                Surface {
                    RootContent()
                }
            }
        }
    }
}

@Composable
private fun RootContent(
    rootViewModel: RootViewModel = koinViewModel(),
) {
    val session by rootViewModel.sessionState.collectAsStateWithLifecycle()

    when (session) {
        SessionState.Loading -> LoadingScreen()
        else -> SessionNavHost(session)
    }
}

@Composable
private fun SessionNavHost(session: SessionState) {
    val backStack = remember {
        mutableStateListOf<NavKey>(
            if (session is SessionState.LoggedIn) HomeKey else LoginKey
        )
    }

    LaunchedEffect(session) {
        val expected: NavKey = if (session is SessionState.LoggedIn) HomeKey else LoginKey
        if (backStack.lastOrNull() != expected) {
            backStack.clear()
            backStack.add(expected)
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<LoginKey> { LoginScreen() }
            entry<HomeKey> {
                HomeScreen(onOpenNotes = { backStack.add(NotesKey) })
            }
            entry<NotesKey> { NotesScreen() }
        },
    )
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
