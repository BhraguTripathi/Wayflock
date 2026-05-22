package com.example.wayflock.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wayflock.R

// Font Family Declaration
val PoppinsFamily = FontFamily(
    Font(R.font.poppins_light,     FontWeight.Light),      // W300 — captions, hints
    Font(R.font.poppins_regular,   FontWeight.Normal),     // W400 — body text
    Font(R.font.poppins_medium,    FontWeight.Medium),     // W500 — labels, nav items
    Font(R.font.poppins_semibold,  FontWeight.SemiBold),   // W600 — card titles, buttons
    Font(R.font.poppins_bold,      FontWeight.Bold),       // W700 — screen headings
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),  // W800 — display text
)


//
// Material 3 defines 15 text roles across 5 categories:
//   Display → Headline → Title → Body → Label
//
// HOW EACH IS USED IN WAYFLOCK:
//
//   displayLarge   — Not used (too large for mobile)
//   displayMedium  — Not used
//   displaySmall   — App name on splash/onboarding (wayflock)
//
//   headlineLarge  — Screen-level titles ("Hi, Bhragu 👋", "Create Trip")
//   headlineMedium — Section headers ("Active Trip", "Recent Trips")
//   headlineSmall  — Trip name on card ("Manali Trip")
//
//   titleLarge     — Dialog titles, bottom sheet titles
//   titleMedium    — Card section headers ("Trip Details", "Members")
//   titleSmall     — Member names in lists ("Arjun Mehta")
//
//   bodyLarge      — Primary body text (descriptions, form content)
//   bodyMedium     — Secondary body text ("Added by you", distances)
//   bodySmall      — Metadata, timestamps, hint text
//
//   labelLarge     — Button text ("Login", "Create Trip", "Join Trip")
//   labelMedium    — Badge text ("LIVE", "Completed"), nav labels
//   labelSmall     — Tiny captions, form helper text, char counters

val WayflockTypography = Typography(

    // ── DISPLAY ───────────────────────────────────────────────────────────────
    // Used for the app name on splash or onboarding hero

    displayLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
    ),

    displayMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
    ),

    // ★ USED: "wayflock" app name on onboarding — large, proud, brand-level text
    displaySmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.5).sp,
    ),


    // ── HEADLINE ──────────────────────────────────────────────────────────────
    // Primary screen titles and hero text

    // ★ USED: "Hi, Bhragu 👋", "Create Trip", "Join Trip", "Members"
    headlineLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.25).sp,
    ),

    // ★ USED: "Travel together, memories forever." — login screen hero text
    headlineMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
    ),

    // ★ USED: Trip name on Trip Details screen ("Manali Trip")
    headlineSmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),


    // ── TITLE ─────────────────────────────────────────────────────────────────
    // Section headers, card titles, dialog titles

    // ★ USED: Bottom sheet / dialog titles
    titleLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp,
    ),

    // ★ USED: "Trip Details", "Members (5)", "Active Trip", "Recent Trips"
    titleMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp,
    ),

    // ★ USED: Member names in list ("Arjun Mehta"), trip names in recent list
    titleSmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),


    // ── BODY ──────────────────────────────────────────────────────────────────
    // Content text, descriptions, form values

    // ★ USED: Trip description, how-to steps text, form field values
    bodyLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
    ),

    // ★ USED: "Added by you", "5 Members", distance labels ("1.2 km away")
    //         Subtitle text under names and in cards
    bodyMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),

    // ★ USED: "Last updated: Just now", timestamps, metadata, "Created by You"
    bodySmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
    ),


    // ── LABEL ─────────────────────────────────────────────────────────────────
    // Buttons, badges, navigation labels, form helper text

    // ★ USED: All button text ("Login", "Create Trip", "Join Trip", "Get Started")
    labelLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp,
    ),

    // ★ USED: "LIVE" badge, "Completed" badge, bottom nav labels ("Home", "Trips")
    //         Tab labels ("Active", "Past", "Invites")
    labelMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),

    // ★ USED: Character counters ("0/30"), form helper hints
    //         ("At least 8 characters with letters and numbers")
    //         Trip code display ("MNLI24")
    labelSmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
)


// =============================================================================
// CUSTOM TEXT STYLES — Beyond the Material 3 scale
// =============================================================================
// These are Wayflock-specific styles that don't map to M3 roles cleanly.
// Use them directly as TextStyle in your Composables.
// =============================================================================

// ★ "wayflock" lowercase brand wordmark — used in headers alongside the logo
val WayflockBrandText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 28.sp,
    lineHeight = 36.sp,
    letterSpacing = (-0.5).sp,
    color = Green500,
)

// ★ Stat numbers on profile screen ("12", "28", "19", "4.8")
val StatNumberText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp,
)

// ★ Stat labels below numbers ("Trips", "Friends", "Destinations", "Rating")
val StatLabelText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.3.sp,
    color = TextSecondary,
)

// ★ Trip code display — the shareable code ("MNLI24")
//   Monospaced-style feel; slightly tracked out
val TripCodeText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 2.sp,       // Wide letter spacing = code-like appearance
    color = Green500,
)

// ★ Distance label on map and member list ("1.2 km away", "650 m away")
val DistanceLabelText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp,
    color = TextSecondary,
)

// ★ LIVE badge label — all caps, tight tracking
val LiveBadgeText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    letterSpacing = 1.sp,
    color = Green700,
)

// ★ Onboarding slide title ("Travel Together, Memories Forever")
val OnboardingTitleText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 26.sp,
    lineHeight = 34.sp,
    letterSpacing = (-0.25).sp,
)

// ★ Onboarding slide subtitle (the descriptive paragraph below title)
val OnboardingSubtitleText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.1.sp,
    color = TextSecondary,
)

// ★ Form field label ("Full Name", "Email", "Password")
val FormLabelText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp,
    color = TextPrimary,
)

// ★ Form hint/helper text ("At least 8 characters with letters and numbers")
val FormHelperText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.3.sp,
    color = TextSecondary,
)

// ★ Section header within a screen ("Active Trip", "Recent Trips", "Members (5)")
val SectionHeaderText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 17.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp,
    color = TextPrimary,
)

// ★ "See All" / "View All" / "View on Map" — tappable secondary actions
val ActionLinkText = TextStyle(
    fontFamily = PoppinsFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp,
    color = Green500,
)