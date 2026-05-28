package com.example.wayflock.ui.screens.trips

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wayflock.R
import com.example.wayflock.ui.components.BottomNavItem
import com.example.wayflock.ui.components.CustomBottomNavigationBar
import com.example.wayflock.ui.components.NatureSceneHeader
import com.example.wayflock.ui.components.UserAvatarWithDot
import com.example.wayflock.ui.theme.Green50
import com.example.wayflock.ui.theme.Green500
import com.example.wayflock.ui.theme.Neutral100
import com.example.wayflock.ui.theme.Neutral400
import com.example.wayflock.ui.theme.WayflockTypography


// Tab Enum
enum class TripTab (
    val label : String,
    val icon : ImageVector,
) {
    ACTIVE("Active", Icons.Rounded.LocationOn),
    PAST("Past", Icons.Outlined.History),
    INVITES("Invites", Icons.Outlined.Email)
}

@Composable
fun TripsScreen(
    onProfileClick : () -> Unit ={},
    onTripClick : () -> Unit ={},
    onViewMap : () -> Unit = {},
    onViewAllPastTrips : () -> Unit ={},
    onViewAllInvites : () -> Unit ={},
    onAcceptInvite : (String) -> Unit = {},
    onDeclineInvite : (String) -> Unit = {},
){
    var selectedTab by remember { mutableStateOf(TripTab.ACTIVE) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            CustomBottomNavigationBar(
                currentRoute = BottomNavItem.Trips,
                onItemSelected = {},
            )
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            // Nature Hero Image
            NatureSceneHeader(
                image = R.drawable.homesectionheader,
                height = 230.dp,
                blurRadius = 1.dp,
                modifier = Modifier.align(Alignment.TopCenter),
            )

            // Main Scrollable Content
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 32.dp
                ),
            ) {

                // Trips title + profile avatar
                item (
                    key = "header"
                ) {
                    TripsHeader(
                        userPhotoUrl = null,
                        onProfileClick = onProfileClick,
                    )
                }

                item(key = "space_after_header") { Spacer(Modifier.height(4.dp)) }

                // Segmented pill tab control
                item(
                    key = "tabs"
                ){
                    TripTabSelector(
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it },
                    )
                }

                item(key = "space_after_tabs") { Spacer(Modifier.height(24.dp)) }

                // Tab Driven Content
                when (selectedTab) {
                    TripTab.ACTIVE -> activeTabContent(
                        onTripClick = onTripClick,
                        onViewMap = onViewMap,
                        onViewAllPastTrips = onViewAllPastTrips,
                        onViewAllInvites = onViewAllInvites,
                        onAcceptInvite = onAcceptInvite,
                        onDeclineInvite = onDeclineInvite,
                    )

                    TripTab.PAST -> pastTabContent(
                        onTripClick = onTripClick,
                    )

                    TripTab.INVITES -> invitesTabContent(
                        onAcceptInvite = onAcceptInvite,
                        onDeclineInvite = onDeclineInvite,
                    )
                }
            }
        }
    }
}

// Trips Header
@Composable
fun TripsHeader(
    userPhotoUrl : String?,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 5.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {

        // Left: Screen title + subtitle
        Column {
            Text(
                text = "Trips",
                style = WayflockTypography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "All your journeys in one place",
                style = WayflockTypography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Right: Profile avatar with online indicator dot
        UserAvatarWithDot(
            imageURL = userPhotoUrl,
            avatarSize = 50.dp,
            dotSize = 13.dp,
            showOnlineDot = true,
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onProfileClick,
                )
                .padding(2.dp)
        )
    }
}

// Trip Tab Selector
@Composable
fun TripTabSelector(
    selectedTab : TripTab,
    onTabSelected : (TripTab) -> Unit,
    modifier: Modifier = Modifier,
){
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(100.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        tonalElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TripTab.enteries.forEach { tab->
                TripTabItem(
                    tab = tab,
                    isSelected = selectedTab == tab,
                    onClick = { onTabSelected(tab) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

// Trip Tab Item
@Composable
fun TripTabItem(
    tab : TripTab,
    isSelected : Boolean,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
){
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(
                color = if (isSelected) Green50 else Color.Transparent,
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(vertical = 10.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = tab.icon,
                contentDescription = tab.label,
                tint = if (isSelected) Green500 else Neutral400,
                modifier = Modifier.size(16.dp),
            )

            Spacer(Modifier.width(6.dp))

            Text(
                text = tab.label,
                style = WayflockTypography.labelMedium,
                color = if (isSelected) Green500 else Neutral400,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
            )
        }
    }
}

// Icon Section Header
@Composable
fun IconSectionHeader(){

}