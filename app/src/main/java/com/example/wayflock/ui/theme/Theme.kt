package com.example.wayflock.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// =============================================================================
// WAYFLOCK — Main Theme File
// =============================================================================
//
// HOW MATERIAL 3 THEMING WORKS (Beginner Explanation):
//
//   Material 3 uses a "ColorScheme" — a set of named color roles.
//   Instead of using raw colors directly, you use ROLE NAMES like:
//     • primary         → the main brand color (our Green500)
//     • onPrimary       → text/icons ON TOP of primary (white)
//     • surface         → card and screen backgrounds
//     • onSurface       → text color on surfaces
//
//   WHY THIS MATTERS:
//   When you use MaterialTheme.colorScheme.primary instead of Color(0xFF2E9E3F),
//   your app automatically adapts to:
//     1. Light ↔ Dark mode switches
//     2. Dynamic color (Android 12+ wallpaper-based theming)
//     3. Future re-theming without touching every composable
//
//   HOW IT FLOWS:
//   WayflockTheme()                   ← wraps your whole app (in MainActivity)
//     └── MaterialTheme(              ← sets up M3 system
//           colorScheme = ...,        ← our color mappings
//           typography  = ...,        ← our Poppins type scale
//           shapes      = ...,        ← our rounded shapes
//         )
//           └── All Composables       ← access via MaterialTheme.colorScheme.*
//
// =============================================================================


// LIGHT COLOR SCHEME : Maps Wayflock brand colors → Material 3 semantic color roles

private val WayflockLightColorScheme = lightColorScheme(

    // ── PRIMARY — The main brand/action color ─────────────────────────────────
    primary            = Green500,          // Main brand green — buttons, active icons
    onPrimary          = NeutralWhite,      // White text/icons ON primary (button labels)
    primaryContainer   = Green100,          // Light green container — icon bg, chips
    onPrimaryContainer = Green800,          // Text/icons inside primary container

    // ── SECONDARY — Supporting accent ─────────────────────────────────────────
    secondary            = Sage400,         // Muted sage green — secondary actions
    onSecondary          = NeutralWhite,    // Text ON secondary
    secondaryContainer   = Sage100,         // Light sage — secondary chip backgrounds
    onSecondaryContainer = Sage500,         // Text inside secondary container

    // ── TERTIARY — Complementary accent ──────────────────────────────────────
    // Not heavily used in Wayflock but needed for M3 completeness
    tertiary            = InfoBlue,         // Blue for info banners ("Back Online")
    onTertiary          = NeutralWhite,
    tertiaryContainer   = InfoBlueLight,
    onTertiaryContainer = InfoBlue,

    // ── ERROR — Destructive / validation states ───────────────────────────────
    error            = ErrorRed,            // Error text, field borders, icons
    onError          = NeutralWhite,        // Text ON error (white on red button)
    errorContainer   = ErrorRedLight,       // Light red bg for error snackbars/banners
    onErrorContainer = ErrorRedDark,        // Text inside error container

    // ── BACKGROUND — The screen background color ──────────────────────────────
    background   = NeutralWhite,            // Main screen background (pure white)
    onBackground = TextPrimary,             // Text drawn directly on background

    // ── SURFACE — Card and sheet backgrounds ─────────────────────────────────
    surface          = NeutralWhite,        // Card surface, bottom sheets
    onSurface        = TextPrimary,         // Text on surface (card content)
    surfaceVariant   = Neutral100,          // Slightly tinted surface — search bars,
    // input fields, segmented control bg
    onSurfaceVariant = Neutral500,          // Secondary text on surfaceVariant

    // ── OUTLINE — Borders and dividers ────────────────────────────────────────
    outline        = Neutral200,            // Input field borders, dividers
    outlineVariant = Neutral100,            // Subtle separators (list item dividers)

    // ── INVERSE — For snackbars and reverse-contrast elements ─────────────────
    inverseSurface      = Neutral800,       // Dark snackbar background
    inverseOnSurface    = Neutral100,       // Light text on dark snackbar
    inversePrimary      = Green300,         // Action text on dark snackbar

    // ── SCRIM — Modal overlay backdrop ────────────────────────────────────────
    scrim = Color(0x80000000),              // 50% black behind dialogs/sheets
)


// DARK COLOR SCHEME
private val WayflockDarkColorScheme = darkColorScheme(

    primary            = Green300,          // Lighter green — more visible on dark bg
    onPrimary          = Green900,          // Dark text ON light green button
    primaryContainer   = Green700,          // Dark container for icon bg
    onPrimaryContainer = Green100,          // Light text inside dark container

    secondary            = Sage300,
    onSecondary          = Sage500,
    secondaryContainer   = Sage400,
    onSecondaryContainer = Sage100,

    tertiary            = InfoBlue,
    onTertiary          = NeutralWhite,
    tertiaryContainer   = Color(0xFF1E3A5F),
    onTertiaryContainer = InfoBlueLight,

    error            = Color(0xFFFF6B6B),   // Softer red — less harsh on dark bg
    onError          = Color(0xFF690005),
    errorContainer   = Color(0xFF93000A),
    onErrorContainer = ErrorRedLight,

    background   = DarkBackground,          // Very dark green-black
    onBackground = DarkOnSurface,

    surface          = DarkSurface,
    onSurface        = DarkOnSurface,
    surfaceVariant   = DarkSurfaceVariant,
    onSurfaceVariant = Neutral400,

    outline        = DarkOutline,
    outlineVariant = Color(0xFF2A3D2F),

    inverseSurface   = Neutral100,
    inverseOnSurface = Neutral800,
    inversePrimary   = Green500,

    scrim = Color(0x99000000),
)


// MAIN THEME COMPOSABLE

@Composable
fun WayflockTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Keep dynamicColor = false to preserve Wayflock's green brand identity.
    // If true, Android 12+ would override our colors with the user's wallpaper colors.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    // ── Step 1: Select the color scheme ──────────────────────────────────────
    val colorScheme: ColorScheme = when {

        // Dynamic color: Android 12+ only — uses wallpaper-extracted colors
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        // Our brand color scheme: green identity preserved
        darkTheme -> WayflockDarkColorScheme
        else      -> WayflockLightColorScheme
    }

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // Make the status bar transparent so the nature hero image shows through
            window.statusBarColor = colorScheme.background.toArgb()

            // Set status bar icon color based on theme
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = WayflockTypography,
        shapes      = WayflockShapes,
        content     = content,
    )
}

val MaterialTheme.appColors: ColorScheme
    @Composable
    @ReadOnlyComposable
    get() = this.colorScheme