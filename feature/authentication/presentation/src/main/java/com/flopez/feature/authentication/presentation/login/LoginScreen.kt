package com.flopez.feature.authentication.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flopez.core.presentation.component.SimpleButton
import com.flopez.core.presentation.mvi.ObserveEffectOnLifeCycle
import com.flopez.core.presentation.theme.VerdantPantryTheme
import com.flopez.core.presentation.theme.VerdantTheme
import com.flopez.feature.authentication.presentation.login.model.LoginScreenContract
import com.flopez.feature.authentication.presentation.login.model.LoginScreenContract.Effect
import com.flopez.feature.authentication.presentation.login.model.LoginScreenContract.Intent
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveEffectOnLifeCycle(
        effect = viewModel.effect
    ) { effect ->
        when (effect) {
            is Effect.ShowToast -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
        }
    }

    LoginContent(
        uiState  = uiState,
        onIntent = viewModel::onIntent,
    )
}

@Composable
private fun LoginContent(
    uiState: LoginScreenContract.State,
    onIntent: (Intent) -> Unit,
) {
    Scaffold (
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
                text  = if (uiState.isRegisterMode) "Crear cuenta" else "Iniciar sesión",
                style = VerdantTheme.typography.headlineSmall,
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value         = uiState.username,
                onValueChange = { onIntent(Intent.OnUserNameChange(it)) },
                label         = { Text("Usuario") },
                singleLine    = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction    = ImeAction.Next,
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value         = uiState.password,
                onValueChange = { onIntent(Intent.OnPasswordChange(it)) },
                label         = { Text("Contraseña") },
                singleLine    = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction    = ImeAction.Done,
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked   = uiState.isRegisterMode,
                    onCheckedChange = { onIntent(Intent.OnRegisterModeToggle) },
                )
                Text(text = "Registrar")
            }

            SimpleButton(
                modifier  = Modifier.fillMaxWidth(),
                text      = if (uiState.isRegisterMode) "Registrar" else "Iniciar sesión",
                onClick   = { onIntent(Intent.OnLoginClick) },
                isLoading = uiState.isLoading,
                isEnabled = !uiState.isLoading,
            )
        }
    }
}

// ─── Previews ───────────────────────────────────────────────

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    VerdantPantryTheme {
        LoginContent(
            uiState  = LoginScreenContract.State(),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterPreview() {
    VerdantPantryTheme {
        LoginContent(
            uiState  = LoginScreenContract.State(isRegisterMode = true),
            onIntent = {},
        )
    }
}
