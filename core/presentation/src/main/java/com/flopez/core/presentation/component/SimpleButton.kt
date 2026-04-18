package com.flopez.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flopez.core.presentation.theme.VerdantPantryTheme
import com.flopez.core.presentation.theme.VerdantTheme

@Composable
fun SimpleButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false
) {
    val buttonEnabled by remember {
        mutableStateOf(!isLoading && isEnabled)
    }

    Button(
        onClick = onClick,
        enabled = buttonEnabled,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp,
                color = VerdantTheme.colorScheme.onPrimary
            )
        } else {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SimpleButtonPreview() {

}


@Preview(showBackground = true)
@Composable
private fun SimpleButtonDisabledPreview() {
    VerdantPantryTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SimpleButton(
                text = "Entrar",
                onClick = {},
            )

            SimpleButton(
                text = "Entrar",
                onClick = {},
                isLoading = true
            )

            SimpleButton(
                text = "Entrar",
                onClick = {},
                isEnabled = false,
            )
        }
    }
}
