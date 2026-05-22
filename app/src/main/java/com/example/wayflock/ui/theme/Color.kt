package com.example.wayflock.ui.theme

import androidx.compose.ui.graphics.Color

// Primary - Forest Green : Used for brand identity, buttons, active status, icons, and key interactive elements

val Green50   = Color(0xFFEBF7EE)   // Lightest tint  — chip backgrounds, subtle fills
val Green100  = Color(0xFFD0EED7)   // Light tint     — icon container backgrounds
val Green200  = Color(0xFFA8DDB5)   // Soft           — progress tracks, dividers
val Green300  = Color(0xFF74C480)   // Medium light   — secondary badges
val Green400  = Color(0xFF4CAF5A)   // Medium         — hover / pressed states
val Green500  = Color(0xFF2E9E3F)   // Core brand   — primary buttons, active nav icons
val Green600  = Color(0xFF278835)   // Slightly dark  — button pressed, outlines
val Green700  = Color(0xFF1E6E29)   // Dark           — dark-mode primary surface
val Green800  = Color(0xFF165420)   // Deeper         — on-dark text
val Green900  = Color(0xFF0C3813)   // Darkest        — extreme dark accents

// Secondary - Sage/Muted Green : Used for illustration background, decorative panel, the nature header tint, & the misty mountain art work area

val Sage50    = Color(0xFFF2F8F3)   // Near-white green  — page backgrounds, card fills
val Sage100   = Color(0xFFE2F0E5)   // Very light sage   — section dividers
val Sage200   = Color(0xFFC5DEC9)   // Soft sage         — illustration fill areas
val Sage300   = Color(0xFF9DC5A3)   // Muted mid-sage    — decorative tree shapes
val Sage400   = Color(0xFF6FA077)   // Medium sage       — illustration mid-tones
val Sage500   = Color(0xFF4E7D56)   // Deep sage         — illustration dark areas

// NEUTRAL — Grays : Text, backgrounds, dividers, inactive states, surfaces.

val NeutralWhite  = Color(0xFFFFFFFF)   // Pure white    — screen background, cards
val Neutral50     = Color(0xFFF8F9FA)   // Off white     — subtle surface lift
val Neutral100    = Color(0xFFF2F3F5)   // Lightest gray — search bar background, chips
val Neutral200    = Color(0xFFE4E6EA)   // Light gray    — dividers, borders, outlines
val Neutral300    = Color(0xFFCDD0D5)   // Soft gray     — placeholder text bg
val Neutral400    = Color(0xFF9EA3AC)   // Medium gray   — placeholder text, icons (off)
val Neutral500    = Color(0xFF6B7280)   // Gray          — body text secondary
val Neutral600    = Color(0xFF4B5563)   // Used for subtitles, metadata
val Neutral700    = Color(0xFF374151)   // Dark gray     — body text primary
val Neutral800    = Color(0xFF1F2937)   // Near-black    — headings
val Neutral900    = Color(0xFF111827)   // Near-black    — display text, app bar titles

// Explicit alias — used in code as semantic names

val TextPrimary       = Neutral900      // Screen titles, bold labels
val TextSecondary     = Neutral500      // Subtitles, metadata, "Added by you"
val TextTertiary      = Neutral400      // Placeholders, disabled labels
val TextOnPrimary     = NeutralWhite    // White text on green buttons
val TextLink          = Green500        // Clickable links — "Forgot Password?", "Sign up"

// ERROR / DESTRUCTIVE : Error messages, destructive actions (Leave Trip, Log Out icon)

val ErrorRed          = Color(0xFFDC2626)   // Primary error — text, icon tint
val ErrorRedLight     = Color(0xFFFEE2E2)   // Background tint for error containers
val ErrorRedDark      = Color(0xFF991B1B)   // Pressed / dark-mode error

// WARNING : Used for "falling behind" alerts, battery-low warnings

val WarningAmber      = Color(0xFFF59E0B)   // Warning icon/text
val WarningAmberLight = Color(0xFFFEF3C7)   // Warning container background

// SUCCESS (Semantic alias of Green) : Online indicators, sync success, "Completed" trip badge

val SuccessGreen      = Green500            // "All members are online" dot
val SuccessGreenLight = Green50             // Success container bg

// INFO : Informational banners, "back online" notifications

val InfoBlue          = Color(0xFF3B82F6)
val InfoBlueLight     = Color(0xFFEFF6FF)


// MAP / LOCATION SPECIFIC : Colors that appear directly on map elements

val MapRouteColor         = Green500            // Dashed route line between members
val MapRouteDash          = Green300            // Lighter dashes on route
val MapDestinationPin     = Green600            // Destination drop pin
val MapUserAvatarBorder   = Green500            // Ring around the user's avatar pin
val MapOtherMemberBorder  = NeutralWhite        // White ring on other members' pins
val MapPinShadow          = Color(0x33000000)   // 20% black — pin drop shadow


// SURFACE & BACKGROUND — Semantic tokens : Use these instead of raw color values throughout the UI

val SurfaceBackground     = NeutralWhite        // Main screen background
val SurfaceCard           = NeutralWhite        // Card / sheet surface
val SurfaceCardElevated   = Neutral50           // Slightly elevated card (e.g. stat row)
val SurfaceSearchBar      = Neutral100          // Search bar background
val SurfaceInputField     = NeutralWhite        // Text field background
val SurfaceDivider        = Neutral200          // Horizontal rule / list separator

val BorderDefault         = Neutral200          // Outlined input field border (idle)
val BorderFocused         = Green500            // Input field border on focus
val BorderError           = ErrorRed            // Input field border on validation error

// COMPONENT SPECIFIC — Badge & Status colors : LIVE badge (green pill on trip cards)
val BadgeLiveBackground   = Green100
val BadgeLiveText         = Green700
val BadgeLiveDot          = Green500

// Completed badge (on past trips)
val BadgeCompletedBackground = Green50
val BadgeCompletedText       = Green600

// Online indicator dot (member avatars)
val OnlineDot             = Green500
val OfflineDot            = Neutral300

// Bottom Navigation
val NavSelected           = Green500            // Active nav icon + label
val NavUnselected         = Neutral400          // Inactive nav icon + label
val NavIndicator          = Green50             // Pill behind selected icon

// DARK THEME — Surface overrides : Material 3 dark theme maps. Used by DarkColorScheme below

val DarkBackground        = Color(0xFF0D1B0F)   // Very dark green-black
val DarkSurface           = Color(0xFF152417)   // Dark card surface
val DarkSurfaceVariant    = Color(0xFF1E3323)   // Slightly lighter surface
val DarkOnSurface         = Color(0xFFE0F0E3)   // Light text on dark surface
val DarkOutline           = Color(0xFF3A5C42)   // Borders in dark mode
