package com.flopez.feature.home.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flopez.core.presentation.component.SimpleButton
import com.flopez.core.presentation.mvi.ObserveEffectOnLifeCycle
import com.flopez.core.presentation.theme.VerdantPantryTheme
import com.flopez.core.presentation.theme.VerdantTheme
import com.flopez.feature.home.presentation.R
import com.flopez.feature.home.presentation.home.model.HomeScreenContract
import com.flopez.feature.home.presentation.home.model.HomeScreenContract.Effect
import com.flopez.feature.home.presentation.home.model.HomeScreenContract.Intent
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveEffectOnLifeCycle(
        effect = viewModel.effect
    ) { effect ->
        when (effect) {
            is Effect.ShowToast -> Toast.makeText(
                context,
                effect.message.asString(context),
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

    HomeContent(
        uiState = uiState,
        onIntent = viewModel::onIntent,
    )
}

@Composable
private fun HomeContent(
    uiState: HomeScreenContract.State,
    onIntent: (Intent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.home_title),
                style = VerdantTheme.typography.headlineSmall,
            )

            uiState.userEmail?.let { email ->
                Spacer(Modifier.height(4.dp))
                Text(
                    text = email,
                    style = VerdantTheme.typography.bodyMedium,
                )
            }

            Spacer(Modifier.height(24.dp))

            SimpleButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.home_logout_button),
                onClick = { onIntent(Intent.OnLogoutClick) },
                isLoading = uiState.isLoggingOut,
                isEnabled = !uiState.isLoggingOut,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    VerdantPantryTheme {
        HomeContent(
            uiState = HomeScreenContract.State(),
            onIntent = {},
        )
    }
}
