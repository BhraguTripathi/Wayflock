package com.example.wayflock.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wayflock.ui.theme.*

// Data structure holding your items and icons
enum class BottomNavItem(
    val label: String,
    val icon: ImageVector
) {
    Home("Home", Icons.Rounded.Home),
    Trips("Trips", Icons.Outlined.WorkOutline), // Suitcase looking icon
    Members("Members", Icons.Outlined.Group),
    Profile("Profile", Icons.Outlined.Person)
}

@Composable
fun CustomBottomNavigationBar(
    currentRoute: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    // Floating Pill-Shaped Container
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp), // Pushes it away from screen edges
        shape = RoundedCornerShape(32.dp),
        color = SurfaceCard, // Uses the surface color from your theme (assumed white)
        border = BorderStroke(1.dp, Neutral200.copy(alpha = 0.5f)),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround, // Distributes items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem.entries.forEach { item ->
                val isSelected = currentRoute == item
                BottomNavigationItem(
                    item = item,
                    isSelected = isSelected,
                    onClick = { onItemSelected(item) }
                )
            }
        }
    }
}

@Composable
private fun BottomNavigationItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // interactionSource is used here to remove the default ripple for a cleaner custom look
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon with a custom pill-shaped background when selected
        Box(
            modifier = Modifier
                .width(64.dp)
                .height(32.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(if (isSelected) Green50 else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = if (isSelected) Green500 else Neutral400,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Text label matching the specified state colors
        Text(
            text = item.label,
            style = WayflockTypography.labelMedium,
            color = if (isSelected) Green500 else Neutral400,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun BottomNavigationBarPreview(){
    CustomBottomNavigationBar(
        currentRoute = BottomNavItem.Home
    ) { }
}