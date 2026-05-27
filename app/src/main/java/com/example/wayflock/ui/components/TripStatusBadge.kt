package com.example.wayflock.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wayflock.ui.theme.*

enum class TripDisplayStatus {
    LIVE,
    COMPLETED,
    UPCOMING
}

@Composable
fun TripStatusBadge(
    status: TripDisplayStatus,
    modifier: Modifier = Modifier,
){

    val (bgColor, textColor, label) = when(status){
        TripDisplayStatus.LIVE -> Triple(
            BadgeLiveBackground,
            BadgeLiveText,
            "LIVE",
        )
        TripDisplayStatus.COMPLETED -> Triple(
            BadgeCompletedBackground,
            BadgeCompletedText,
            "Completed",
        )
        TripDisplayStatus.UPCOMING -> Triple(
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.onSecondaryContainer,
            "Upcoming",
        )
    }

    Surface(
        modifier = modifier,
        shape = BadgeShape,
        color = bgColor
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = WayflockTypography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
        )
    }
}