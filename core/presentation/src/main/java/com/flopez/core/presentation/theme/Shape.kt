package com.flopez.core.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// ─────────────────────────────────────────────────────────────
//  Verdant Shape scale — Material 3
//  Slightly rounder than M3 defaults to feel organic/friendly
// ─────────────────────────────────────────────────────────────
val VerdantShapes = Shapes(
    // ExtraSmall — input fields, small chips
    extraSmall = RoundedCornerShape(4.dp),

    // Small — text fields, small cards, snackbars
    small = RoundedCornerShape(8.dp),

    // Medium — cards, dialogs, menus (default M3 = 12dp)
    medium = RoundedCornerShape(16.dp),

    // Large — bottom sheets, nav drawers, large cards
    large = RoundedCornerShape(20.dp),

    // ExtraLarge — FAB, hero surfaces
    extraLarge = RoundedCornerShape(28.dp),
)
