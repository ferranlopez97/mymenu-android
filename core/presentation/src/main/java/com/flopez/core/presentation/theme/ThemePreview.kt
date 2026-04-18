package com.flopez.core.presentation.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ─────────────────────────────────────────────────────────────
//  Component previews — shows the theme in Android Studio
// ─────────────────────────────────────────────────────────────

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF121412)
@Composable
fun PreviewDark() {
    VerdantPantryTheme(darkTheme = true) {
        ThemeShowcase()
    }
}

@Preview(name = "Light Theme", showBackground = true)
@Composable
fun PreviewLight() {
    VerdantPantryTheme(darkTheme = false) {
        ThemeShowcase()
    }
}

@Composable
fun ThemeShowcase() {
    Surface(
        color = VerdantTheme.colorScheme.background,
        contentColor = VerdantTheme.colorScheme.onBackground,
    ) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Typography
        Text("Mi menú semanal", style = VerdantTheme.typography.headlineMedium)
        Text(
            "Planifica tus comidas", style = VerdantTheme.typography.titleMedium,
            color = VerdantTheme.colorScheme.onSurfaceVariant
        )
        Text(
            "Selecciona los días para generar tu lista de la compra automáticamente.",
            style = VerdantTheme.typography.bodyMedium,
            color = VerdantTheme.colorScheme.onSurfaceVariant
        )

        HorizontalDivider(color = VerdantTheme.colorScheme.outlineVariant)

        // Buttons
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {}) { Text("Generar lista") }
            FilledTonalButton(onClick = {}) { Text("Ver menú") }
            OutlinedButton(onClick = {}) { Text("Editar") }
        }

        // Chips
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = true, onClick = {}, label = { Text("Almuerzo") })
            FilterChip(selected = false, onClick = {}, label = { Text("Cena") })
            FilterChip(selected = false, onClick = {}, label = { Text("Desayuno") })
        }

        // Card
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Ensalada César", style = VerdantTheme.typography.titleMedium)
                Text(
                    "Almuerzo · Lunes", style = VerdantTheme.typography.labelMedium,
                    color = VerdantTheme.colorScheme.primary
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Lechuga, pollo, parmesano, croutons, salsa César",
                    style = VerdantTheme.typography.bodySmall
                )
            }
        }

        // FAB
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Añadir comida")
            }
            SmallFloatingActionButton(onClick = {}) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Lista")
            }
        }
    }
    }
}
