package com.flopez.core.presentation.theme


import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


// ─────────────────────────────────────────────────────────────
//  DARK color scheme — Verdant Pantry Nocturnal (primary)
// ─────────────────────────────────────────────────────────────
val VerdantDarkColorScheme = darkColorScheme(
    // Primary
    primary                = Green60,   // #4CAF50
    onPrimary              = Green10,   // #002106
    primaryContainer       = Green30,   // #1B3D1E
    onPrimaryContainer     = Green80,   // #8ED98F
    inversePrimary         = Green40,   // #2A5C2E

    // Secondary
    secondary              = Sage80,    // #91BF8C
    onSecondary            = Sage20,    // #1B3B19
    secondaryContainer     = Sage30,    // #2A522D
    onSecondaryContainer   = Sage90,    // #CCEDC8

    // Tertiary
    tertiary               = Pink80,    // #E89BC0
    onTertiary             = Pink20,    // #4A1033
    tertiaryContainer      = Pink30,    // #65264A
    onTertiaryContainer    = Pink90,    // #FFD8E9

    // Error
    error                  = Color(0xFFFFB4AB),
    onError                = Color(0xFF690005),
    errorContainer         = Color(0xFF93000A),
    onErrorContainer       = Color(0xFFFFDAD6),

    // Background & Surface
    background             = Neutral20,  // #121412
    onBackground           = Green90,    // #C8F0C8
    surface                = Neutral20,  // #121412
    onSurface              = Green90,    // #C8F0C8
    surfaceVariant         = NeutralVar20, // #1A2B1B
    onSurfaceVariant       = NeutralVar80, // #9AB896
    surfaceTint            = Green60,

    // Inverse
    inverseSurface         = Neutral90,
    inverseOnSurface       = Neutral30,

    // Outline
    outline                = NeutralVar50,  // #4E7050
    outlineVariant         = NeutralVar30,  // #2A3C2B

    // Scrim
    scrim                  = Neutral0,
)

// ─────────────────────────────────────────────────────────────
//  LIGHT color scheme — Verdant Pantry (for completeness)
// ─────────────────────────────────────────────────────────────
val VerdantLightColorScheme = lightColorScheme(
    // Primary
    primary                = Green40,   // #2A5C2E
    onPrimary              = Green100,
    primaryContainer       = Green90,   // #C8F0C8
    onPrimaryContainer     = Green10,   // #002106
    inversePrimary         = Green80,

    // Secondary
    secondary              = Sage40,    // #3D6B40
    onSecondary            = Sage100,
    secondaryContainer     = Sage90,    // #CCEDC8
    onSecondaryContainer   = Sage10,    // #062109

    // Tertiary
    tertiary               = Pink40,    // #7D3D5E
    onTertiary             = Pink100,
    tertiaryContainer      = Pink90,    // #FFD8E9
    onTertiaryContainer    = Pink10,    // #3B0029

    // Error
    error                  = Color(0xFFBA1A1A),
    onError                = Color(0xFFFFFFFF),
    errorContainer         = Color(0xFFFFDAD6),
    onErrorContainer       = Color(0xFF410002),

    // Background & Surface
    background             = Green99,   // #F5FEF5
    onBackground           = Neutral10,
    surface                = Green99,
    onSurface              = Neutral10,
    surfaceVariant         = NeutralVar95, // #DEEFDB
    onSurfaceVariant       = NeutralVar30, // #2A3C2B
    surfaceTint            = Green40,

    // Inverse
    inverseSurface         = Neutral25,
    inverseOnSurface       = Neutral95,

    // Outline
    outline                = NeutralVar50,
    outlineVariant         = NeutralVar80,

    // Scrim
    scrim                  = Neutral0,
)
