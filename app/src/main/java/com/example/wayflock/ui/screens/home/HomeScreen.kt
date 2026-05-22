package com.example.wayflock.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.wayflock.ui.components.BottomNavItem
import com.example.wayflock.ui.components.CustomBottomNavigationBar
import com.example.wayflock.ui.theme.*

// Data Models
data class Trip(
    val id: String,
    val name: String,
    val location: String? = null,
    val membersCount: Int? = null,
    val imageUrl: String,
    val isLive: Boolean = false,
    val status: String? = null,
    val lastUpdated: String? = null,
    val dateRange: String? = null,
    val isCompleted: Boolean = false
)

@Composable
fun HomeScreen() {
    val activeTrip = Trip(
        id = "1",
        name = "Manali Trip",
        location = "Manali, Himachal Pradesh",
        membersCount = 5,
        imageUrl = "https://images.unsplash.com/photo-1626621341517-bbf3d9990a23",
        isLive = true,
        status = "All members are online",
        lastUpdated = "Just now"
    )

    val recentTrips = listOf(
        Trip(id = "2", name = "Goa Trip", imageUrl = "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2", dateRange = "10–15 May 2024", isCompleted = true),
        Trip(id = "3", name = "Rishikesh Trip", imageUrl = "https://images.unsplash.com/photo-1566438480900-0609be27a4be", dateRange = "22–24 Apr 2024", isCompleted = true),
        Trip(id = "4", name = "Kasol Trip", imageUrl = "https://images.unsplash.com/photo-1605649440416-43f942f6f1a9", dateRange = "5–7 Apr 2024", isCompleted = true)
    )

    var currentTab by remember { mutableStateOf(BottomNavItem.Home) }

    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(
                currentRoute = currentTab,
                onItemSelected = { selectedItem ->
                    currentTab = selectedItem
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().background(SurfaceBackground).padding(paddingValues)) {
            HomeHeaderBackground()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp)
            ) {
                item { HomeHeader() }
                item { Spacer(modifier = Modifier.height(32.dp)) }
                item { QuickActionsRow() }
                item { Spacer(modifier = Modifier.height(32.dp)) }
                item { SectionHeader(title = "Active Trip", actionLabel = "View on Map", onActionClick = {}, showMapIcon = true) }
                item { Spacer(modifier = Modifier.height(12.dp)) }
                item { ActiveTripCard(trip = activeTrip) }
                item { Spacer(modifier = Modifier.height(32.dp)) }
                item { SectionHeader(title = "Recent Trips", actionLabel = "See All", onActionClick = {}) }
                item { Spacer(modifier = Modifier.height(12.dp)) }
                items(recentTrips) { trip ->
                    RecentTripItem(trip = trip)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun HomeHeaderBackground() {
    Box(modifier = Modifier.fillMaxWidth().height(280.dp).background(
        Brush.verticalGradient(colors = listOf(Green50, SurfaceBackground), startY = 0f, endY = 800f)
    )) {
        AsyncImage(
            model = "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b",
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
    }
}

@Composable
fun HomeHeader() {
    Row(modifier = Modifier.fillMaxWidth().statusBarsPadding(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(text = buildAnnotatedString {
                append("Hi, ")
                withStyle(style = SpanStyle(color = Green500)) { append("Bhragu") }
                append(" 👋")
            }, style = WayflockTypography.headlineLarge)
            Text(text = "Where are we heading today?", style = WayflockTypography.bodyLarge, color = TextSecondary)
        }
        AsyncImage(
            model = "https://randomuser.me/api/portraits/men/32.jpg",
            contentDescription = "Profile",
            modifier = Modifier.size(48.dp).clip(CircleShape).background(Neutral200),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun QuickActionsRow() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        QuickActionCard(modifier = Modifier.weight(1f), title = "Create Trip", description = "Create a new trip and invite your friends", icon = Icons.Rounded.PersonAdd, backgroundColor = Green50, iconColor = Green500)
        QuickActionCard(modifier = Modifier.weight(1f), title = "Join Trip", description = "Join an existing trip using a trip code", icon = Icons.Rounded.GroupAdd, backgroundColor = Green50, iconColor = Green500)
    }
}

@Composable
fun QuickActionCard(modifier: Modifier, title: String, description: String, icon: ImageVector, backgroundColor: Color, iconColor: Color) {
    Card(modifier = modifier, shape = CardShapeDefault, colors = CardDefaults.cardColors(containerColor = SurfaceCard), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.size(40.dp).clip(IconContainerShape).background(backgroundColor), contentAlignment = Alignment.Center) {
                Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = title, style = WayflockTypography.titleMedium, color = TextPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description, style = WayflockTypography.bodySmall, color = TextSecondary, lineHeight = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(Green50).align(Alignment.End), contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null, tint = Green500, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, actionLabel: String, onActionClick: () -> Unit, showMapIcon: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = title, style = SectionHeaderText)
        TextButton(onClick = onActionClick, contentPadding = PaddingValues(0.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = actionLabel, style = ActionLinkText)
                if (showMapIcon) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.Rounded.Map, contentDescription = null, tint = Green500, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun ActiveTripCard(trip: Trip) {
    Card(modifier = Modifier.fillMaxWidth(), shape = CardShapeFeatured, colors = CardDefaults.cardColors(containerColor = SurfaceCard), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                AsyncImage(model = trip.imageUrl, contentDescription = null, modifier = Modifier.size(80.dp).clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = trip.name, style = WayflockTypography.headlineSmall, color = TextPrimary)
                        if (trip.isLive) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(shape = BadgeShape, color = BadgeLiveBackground) {
                                Text(text = "LIVE", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), style = LiveBadgeText)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.People, null, tint = TextSecondary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("${trip.membersCount} Members", style = WayflockTypography.bodyMedium, color = TextSecondary)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.LocationOn, null, tint = TextSecondary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(trip.location ?: "", style = WayflockTypography.bodyMedium, color = TextSecondary)
                    }
                }
                Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Green50), contentAlignment = Alignment.Center) {
                    Icon(Icons.Rounded.ChevronRight, null, tint = Green500, modifier = Modifier.size(24.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(color = Neutral100)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(OnlineDot))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(trip.status ?: "", style = WayflockTypography.bodySmall, color = TextSecondary)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Last updated: ${trip.lastUpdated}", style = WayflockTypography.bodySmall, color = TextSecondary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Rounded.SignalCellularAlt, null, tint = Green500, modifier = Modifier.size(14.dp))
                }
            }
        }
    }
}

@Composable
fun RecentTripItem(trip: Trip) {
    Card(modifier = Modifier.fillMaxWidth(), shape = CardShapeDefault, colors = CardDefaults.cardColors(containerColor = SurfaceCard), elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(model = trip.imageUrl, contentDescription = null, modifier = Modifier.size(56.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(trip.name, style = WayflockTypography.titleMedium, color = TextPrimary)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.CalendarToday, null, tint = TextSecondary, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(trip.dateRange ?: "", style = WayflockTypography.bodySmall, color = TextSecondary)
                }
            }
            if (trip.isCompleted) {
                Surface(shape = BadgeShape, color = BadgeCompletedBackground) {
                    Text("Completed", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), style = WayflockTypography.labelMedium, color = BadgeCompletedText)
                }
            }
        }
    }
}

@Composable
fun HomeBottomNavigation() {
    NavigationBar(containerColor = SurfaceCard, tonalElevation = 8.dp) {
        NavigationBarItem(icon = { Icon(Icons.Rounded.Home, null) }, label = { Text("Home", style = WayflockTypography.labelMedium) }, selected = true, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Green500, unselectedIconColor = Neutral400, selectedTextColor = Green500, unselectedTextColor = Neutral400, indicatorColor = Green50))
        NavigationBarItem(icon = { Icon(Icons.Outlined.Explore, null) }, label = { Text("Trips", style = WayflockTypography.labelMedium) }, selected = false, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Green500, unselectedIconColor = Neutral400, selectedTextColor = Green500, unselectedTextColor = Neutral400, indicatorColor = Green50))
        NavigationBarItem(icon = { Icon(Icons.Outlined.Group, null) }, label = { Text("Members", style = WayflockTypography.labelMedium) }, selected = false, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Green500, unselectedIconColor = Neutral400, selectedTextColor = Green500, unselectedTextColor = Neutral400, indicatorColor = Green50))
        NavigationBarItem(icon = { Icon(Icons.Outlined.Person, null) }, label = { Text("Profile", style = WayflockTypography.labelMedium) }, selected = false, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Green500, unselectedIconColor = Neutral400, selectedTextColor = Green500, unselectedTextColor = Neutral400, indicatorColor = Green50))
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}