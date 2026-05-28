package com.example.wayflock.ui.components

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.SignalCellularAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wayflock.domain.model.Trip
import com.example.wayflock.ui.theme.BadgeLiveBackground
import com.example.wayflock.ui.theme.BadgeLiveText
import com.example.wayflock.ui.theme.BadgeShape
import com.example.wayflock.ui.theme.CardShapeFeatured
import com.example.wayflock.ui.theme.LiveBadgeText
import com.example.wayflock.ui.theme.OnlineDot
import com.example.wayflock.ui.theme.WayflockTypography

// ACTIVE TRIP CARD
@Composable
fun ActiveTripCard(
    trip: Trip,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = CardShapeFeatured,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column {

            // ── Top Row: Thumbnail + Info + Arrow ─────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.Top,
            ) {
                // Trip cover image
                TripThumbnail(
                    imageUrl = trip.imageUrl,
                    size = 80.dp,
                    cornerRadius = 12.dp,
                )

                Spacer(Modifier.width(14.dp))

                // Trip name, LIVE badge, members, location
                Column(modifier = Modifier.weight(1f)) {

                    // Trip name + LIVE badge on same row
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = trip.name,
                            style = WayflockTypography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        if (trip.isLive == true) {
                            Spacer(Modifier.width(8.dp))
                            LiveBadge()
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    // Members count row
                    IconLabelRow(
                        icon  = Icons.Rounded.People,
                        label = "${trip.membersCount ?: 0} Members",
                    )

                    Spacer(Modifier.height(4.dp))

                    // Location row
                    IconLabelRow(
                        icon  = Icons.Rounded.LocationOn,
                        label = trip.location.orEmpty(),
                    )
                }

                Spacer(Modifier.width(8.dp))

                // Circular arrow button — tapping this also triggers onClick via Card
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector        = Icons.Rounded.ChevronRight,
                        contentDescription = "Open trip",
                        tint               = MaterialTheme.colorScheme.primary,
                        modifier           = Modifier.size(20.dp),
                    )
                }
            }

            // ── Divider ───────────────────────────────────────────────────
            HorizontalDivider(
                color     = MaterialTheme.colorScheme.outlineVariant,
                thickness = 0.8.dp,
            )

            // ── Bottom Footer: Status + Last Updated ─────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically,
            ) {
                // Left: Pulsing dot + status text
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PulsingDot()
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text  = trip.status.orEmpty(),
                        style = WayflockTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                // Vertical rule separator
                VerticalDivider(
                    modifier  = Modifier.height(14.dp),
                    color     = MaterialTheme.colorScheme.outlineVariant,
                    thickness = 0.8.dp,
                )

                // Right: Last updated timestamp + signal icon
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text  = "Updated ${trip.lastUpdated}",
                        style = WayflockTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        imageVector        = Icons.Rounded.SignalCellularAlt,
                        contentDescription = null,
                        tint               = MaterialTheme.colorScheme.primary,
                        modifier           = Modifier.size(14.dp),
                    )
                }
            }
        }
    }
}

// PULSING DOT
@Composable
fun PulsingDot(
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue  = 1.5f,
        animationSpec = infiniteRepeatable(
            animation  = tween(700, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "dotScale",
    )
    Box(
        modifier = modifier
            .size(9.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(OnlineDot)
    )
}

// LIVE BADGE
@Composable
fun LiveBadge(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape    = BadgeShape,
        color    = BadgeLiveBackground,
    ) {
        Text(
            text     = "LIVE",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            style    = LiveBadgeText,
            color    = BadgeLiveText,
        )
    }
}

// ICON LABEL ROW
@Composable
fun IconLabelRow(
    icon:      ImageVector,
    label:     String,
    modifier:  Modifier = Modifier,
    iconSize:  Dp        = 15.dp,
    textStyle: TextStyle = WayflockTypography.bodyMedium,
) {
    Row(
        modifier          = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector        = icon,
            contentDescription = null,
            tint               = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier           = Modifier.size(iconSize),
        )
        Spacer(Modifier.width(5.dp))
        Text(
            text  = label,
            style = textStyle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ActiveTripCardPreview() {
    ActiveTripCard(
        trip = Trip(
            id           = "1",
            name         = "Manali Trip",
            location     = "Manali, Himachal Pradesh",
            membersCount = 5,
            imageUrl     = "https://images.unsplash.com/photo-1626621341517-bbf3d9990a23?w=400",
            isLive       = true,
            status       = "All members are online",
            lastUpdated  = "Just now",
        ),
        onClick = {}
    )
}