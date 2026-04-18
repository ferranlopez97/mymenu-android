package com.flopez.core.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ─────────────────────────────────────────────────────────────
//  VerdantPantryTheme
//
//  Usage:
//    VerdantPantryTheme {
//        Scaffold { ... }
//    }
//
//  Parameters:
//    darkTheme       — follow system setting by default
//    dynamicColor    — use Android 12+ wallpaper colors (disabled
//                      by default to preserve brand identity)
// ─────────────────────────────────────────────────────────────
@Composable
fun VerdantPantryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) VerdantDarkColorScheme else VerdantLightColorScheme

    // Update status bar color to match the background
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = VerdantTypography,
        shapes      = VerdantShapes,
        content     = content,
    )
}

// ─────────────────────────────────────────────────────────────
//  VerdantTheme — accesor propio del design system.
//  Úsalo en vez de MaterialTheme dentro de componentes Verdant.
// ─────────────────────────────────────────────────────────────
object VerdantTheme {
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}
