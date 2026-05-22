package com.example.wayflock.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// =============================================================================
// WAYFLOCK — Shape System
// =============================================================================
//
// WHY SHAPES MATTER:
//   Shapes communicate personality. Looking at the Wayflock designs:
//   • Everything is ROUNDED — cards, buttons, inputs, badges, avatars
//   • No sharp corners anywhere — the app feels soft, friendly, approachable
//   • This matches the "travel together" brand — warm, welcoming, human
//
// DESIGN ANALYSIS FROM MOCKUPS:
//   • Full-width buttons → very rounded (28dp corner) = pill-like
//   • Cards → moderately rounded (16dp corner)
//   • Input fields → rounded (12dp corner)
//   • Badges (LIVE, Completed) → very rounded (100dp = capsule/pill)
//   • Member avatars → perfect circle
//   • Bottom sheet / white form overlay → top corners only (24dp)
//   • Search bars → rounded (12dp corner)
//   • Bottom navigation → no rounding (sits at edge)
//
// MATERIAL 3 SHAPE SCALE:
//   ExtraSmall  → 4dp   — Chips, tooltips
//   Small       → 8dp   — Small buttons, snackbars
//   Medium      → 12dp  — Input fields, search bars, small cards
//   Large       → 16dp  — Cards, dialogs, bottom sheets (partial)
//   ExtraLarge  → 28dp  — Full-width buttons (FAB, primary CTA)
//   Full        → 50%   — Circular elements (avatars, round icon buttons)
// =============================================================================



// Material 3 Shapes — used by MaterialTheme.shapes.

val WayflockShapes = Shapes(

    // ExtraSmall — Smallest interactive surface
    // ★ USED: Tooltip bubbles, small tags
    extraSmall = RoundedCornerShape(4.dp),

    // Small — Icon buttons, compact chips
    // ★ USED: Filter chips on map, small action buttons
    small = RoundedCornerShape(8.dp),

    // Medium — Input fields, search bars, small modals
    // ★ USED: Text input fields (email, password, trip code input)
    //         Search bar on Members screen
    medium = RoundedCornerShape(12.dp),

    // Large — Primary cards, dialogs, drawers
    // ★ USED: Trip cards (Home, Trips screen), Member list items,
    //         Profile settings rows, "How to Join?" card on Join screen
    large = RoundedCornerShape(16.dp),

    // ExtraLarge — Hero components, bottom sheets
    // ★ USED: White form overlay on Login/SignUp that slides over the image
    //         Bottom sheet top corners
    extraLarge = RoundedCornerShape(24.dp),
)


// CUSTOM SHAPES — Component-specific shapes not in the M3 scale
// Use these by name directly in your Composable's `shape` parameter


// ── BUTTONS ──────────────────────────────────────────────────────────────────

// ★ Primary CTA button ("Login", "Create Trip", "Join Trip", "Get Started", "Next")
//   The distinctive pill shape that matches the designs exactly
val ButtonShapePrimary = RoundedCornerShape(100.dp)     // Perfect pill

// ★ Secondary / outlined button ("Stop Sharing", "View on Map", "Decline", "Accept")
val ButtonShapeSecondary = RoundedCornerShape(100.dp)   // Same pill — consistent

// ★ Social auth button ("Continue with Google", "Continue with Apple")
val ButtonShapeSocial = RoundedCornerShape(12.dp)       // Slightly less rounded


// ── INPUT FIELDS ─────────────────────────────────────────────────────────────

// ★ Text input fields — Email, Password, Trip Name, Description, etc.
val InputFieldShape = RoundedCornerShape(12.dp)

// ★ Search bar on Members screen and Create Trip destination search
val SearchBarShape = RoundedCornerShape(100.dp)         // Fully rounded search bar


// ── CARDS ─────────────────────────────────────────────────────────────────────

// ★ Standard content card (trip cards, member items, setting rows)
val CardShapeDefault = RoundedCornerShape(16.dp)

// ★ Active trip card / featured card with image — slightly more rounded
val CardShapeFeatured = RoundedCornerShape(20.dp)

// ★ Small compact card (stat cell on profile, badge container)
val CardShapeSmall = RoundedCornerShape(12.dp)

// ★ How-to step number circle on Join screen ("1", "2", "3")
val StepNumberShape = CircleShape


// ── BADGES ────────────────────────────────────────────────────────────────────

// ★ "LIVE" badge, "Completed" badge — fully rounded pill/capsule shape
val BadgeShape = RoundedCornerShape(100.dp)

// ★ Tab selector pill (Active / Past / Invites segmented control)
val TabIndicatorShape = RoundedCornerShape(100.dp)

// ★ Online/Offline status dot — tiny circle behind the dot
val StatusDotShape = CircleShape


// ── BOTTOM SHEETS & OVERLAYS ─────────────────────────────────────────────────

// ★ White form card overlay on Login and Sign Up screens
//   Only top corners are rounded — the bottom sits flush with screen edge
val BottomCardOverlayShape = RoundedCornerShape(
    topStart = 28.dp,
    topEnd   = 28.dp,
    bottomStart = 0.dp,
    bottomEnd   = 0.dp,
)

// ★ Bottom sheet on Live Map screen (member list panel)
val BottomSheetShape = RoundedCornerShape(
    topStart = 24.dp,
    topEnd   = 24.dp,
    bottomStart = 0.dp,
    bottomEnd   = 0.dp,
)

// ★ Popup / tooltip on map (e.g. "Mall Road, Manali — 650m away")
val MapPopupShape = RoundedCornerShape(12.dp)


// ── AVATARS & ICONS ──────────────────────────────────────────────────────────

// ★ Member profile photo — always a perfect circle
val AvatarShape = CircleShape

// ★ Circular icon container (the green square-ish background behind icons
//   on settings rows and trip detail sections)
val IconContainerShape = RoundedCornerShape(10.dp)

// ★ Navigation item indicator pill (the background that appears
//   behind the active bottom nav icon)
val NavIndicatorShape = RoundedCornerShape(100.dp)


// ── MINI MAP PREVIEW ─────────────────────────────────────────────────────────

// ★ The embedded map preview on Create Trip screen
val MiniMapShape = RoundedCornerShape(12.dp)


// ── SECTION CONTAINERS ───────────────────────────────────────────────────────

// ★ White grouped settings section (Profile screen rows grouped together)
val SettingsSectionShape = RoundedCornerShape(16.dp)

// ★ QR code scan option container on Join Trip screen
val QRContainerShape = RoundedCornerShape(16.dp)

// ★ Trip info card within the bottom sheet on Live Map
val TripInfoCardShape = RoundedCornerShape(20.dp)