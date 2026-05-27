package com.example.wayflock.ui.screens.home

import android.app.Activity
import android.graphics.Color
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.GroupAdd
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material.icons.rounded.SignalCellularAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.wayflock.R
import com.example.wayflock.domain.model.Trip
import com.example.wayflock.ui.components.BottomNavItem
import com.example.wayflock.ui.components.CustomBottomNavigationBar
import com.example.wayflock.ui.components.NatureSceneHeader
import com.example.wayflock.ui.components.SectionHeader
import com.example.wayflock.ui.components.TripDisplayStatus
import com.example.wayflock.ui.components.TripStatusBadge
import com.example.wayflock.ui.components.TripThumbnail
import com.example.wayflock.ui.components.UserAvatarWithDot
import com.example.wayflock.ui.theme.BadgeLiveBackground
import com.example.wayflock.ui.theme.BadgeLiveText
import com.example.wayflock.ui.theme.BadgeShape
import com.example.wayflock.ui.theme.CardShapeDefault
import com.example.wayflock.ui.theme.CardShapeFeatured
import com.example.wayflock.ui.theme.IconContainerShape
import com.example.wayflock.ui.theme.LiveBadgeText
import com.example.wayflock.ui.theme.OnlineDot
import com.example.wayflock.ui.theme.WayflockTheme
import com.example.wayflock.ui.theme.WayflockTypography

@Composable
fun HomeScreen(
    onCreateTrip : () -> Unit = {},
    onJoinTrip : () -> Unit = {},
    onViewMap : () -> Unit = {},
    onSeeAll : () -> Unit = {},
    onTripClick : (String) -> Unit = {},
    onProfileClick : () -> Unit = {},
){
    val activeTrip = remember { sampleActiveTrip }
    val recentTrip = remember { sampleRecentTrip }

    val view = LocalView.current
//    val isDark = isSystemInDarkTheme()

    var currentTab by remember { mutableStateOf(BottomNavItem.Home) }

    DisposableEffect(view) {
        val window = (view.context as Activity).window
        val windowInsetsController = WindowCompat.getInsetsController(window, view)

        window.statusBarColor = Color.TRANSPARENT

        // TODO: Change `false` to `!isDark` when implementing dark mode depending on image brightness
        windowInsetsController.isAppearanceLightStatusBars = false

        onDispose {
            window.statusBarColor = Color.WHITE
            // TODO: Change 'true' to '!isDark' when implementing dark mode
            windowInsetsController.isAppearanceLightStatusBars = true
        }
    }

    Scaffold(
        // WHY explicit containerColor?
        // Without it, Scaffold may use M3's default surface tint which adds
        // a slight blue tint on Android 12+. We want our exact background.

        containerColor = MaterialTheme.colorScheme.background,

        bottomBar = {
            CustomBottomNavigationBar(
                currentRoute = currentTab,
                onItemSelected = { currentTab = it }
            )
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            NatureSceneHeader(
                image = R.drawable.homesectionheader,
                height = 260.dp,
                blurRadius = 1.dp,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 24.dp
                ),
            ) {
                 // Greeting Header
                item("header"){
                    HomeHeader(
                        userName = "Bhragu",
                        userPhotoUrl = null,
                        onProfileClick = onProfileClick
                    )
                }

                item(key = "space_actions") { Spacer(Modifier.height(4.dp)) }

                // Create Trip / Join Trip cards
                item("actions"){
                    QuickActionRow(
                        onCreateTrip = onCreateTrip,
                        onJoinTrip = onJoinTrip,
                    )
                }

                item(key = "space_active") { Spacer(Modifier.height(32.dp)) }

                // Active Trip Section Header
                item("active header") {
                    SectionHeader(
                        title = "Active Trip",
                        actionLabel = "View on Map",
                        onActionClick = onViewMap,
                        showMapIcon = true,
                    )
                }

                item(key = "space_active_card") { Spacer(Modifier.height(12.dp)) }

                // Active Trip Card
                item("active card"){
                    ActiveTripCard(
                        trip = activeTrip,
                        onClick = { onTripClick(activeTrip.id) }
                    )
                }

                item(key = "space_recent") { Spacer(Modifier.height(32.dp)) }

                // Recent Trip Section Header
                item("recent_header"){
                    SectionHeader(
                        title = "Recent Trips",
                        actionLabel = "See All",
                        onActionClick = onSeeAll,
                    )
                }

                item(key = "space_recent_list") { Spacer(Modifier.height(12.dp)) }

                // Recent Trips List
                items(
                    items = recentTrip,
                    key   = { it.id },
                ) { trip ->
                    RecentTripItem(
                        trip    = trip,
                        onClick = { onTripClick(trip.id) },
                    )
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}

// Home Header
@Composable
fun HomeHeader(
    userName : String,
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
        verticalAlignment     = Alignment.Top,
    ) {
        // ── Greeting text ─────────────────────────────────────────────────
        Column {
            Text(
                text = buildAnnotatedString {
                    append("Hi, ")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(userName)
                    }
                    append(" 👋")
                },
                style = WayflockTypography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text  = "Where are we heading today?",
                style = WayflockTypography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        // ── Profile avatar — UserAvatarWithDot is SHARED ──────────────────
        UserAvatarWithDot(
            imageURL      = userPhotoUrl,
            avatarSize    = 50.dp,
            dotSize       = 13.dp,
            showOnlineDot = true,
            modifier      = Modifier
                .clip(CircleShape)
                // Makes the entire avatar tappable
                .then(
                    Modifier.padding(2.dp) // breathing room around the avatar
                ),
        )
    }
}

// Quick Action Row
@Composable
fun QuickActionRow(
    onCreateTrip: () -> Unit,
    onJoinTrip: () -> Unit,
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        QuickActionCard(
            title = "Create Trip",
            description = "Create a new trip and invite your friends",
            icon = Icons.Rounded.PersonAdd,
            onClick = onCreateTrip,
            modifier = Modifier.weight(1f),
        )
        QuickActionCard(
            title = "Join Trip",
            description = "Join an existing trip using a trip code",
            icon = Icons.Rounded.GroupAdd,
            onClick = onCreateTrip,
            modifier = Modifier.weight(1f),
        )
    }
}

// Quick Action Card
@Composable
fun QuickActionCard(
    title : String,
    description : String,
    icon : ImageVector,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
){
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = CardShapeDefault,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        border = CardDefaults.outlinedCardBorder(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            // Icon badge row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(IconContainerShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                DecorativeMemberDots()
            }

            Spacer(Modifier.height(16.dp))

            // Title
            Text(
                text = title,
                style = WayflockTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(6.dp))

            // Description
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = description,
                    style = WayflockTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 17.sp,
                    modifier = Modifier.weight(1f),
                )

                Spacer(Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ChevronRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DecorativeMemberDots(
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier.size(
            width = 54.dp,
            height = 26.dp
        )
    ) {
        val alphas = listOf(1.0f, 0.65f, 0.38f)
        alphas.forEachIndexed { index, alpha ->
            Box(
                modifier = Modifier
                    .offset(x = (index*16).dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = alpha)
                    ),
            )
        }
    }
}

// Active Trip Card
@Composable
fun ActiveTripCard(
    trip : Trip,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
){
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

            // Top Content Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.Top
            ) {
                // Thumbnail
                TripThumbnail(
                    imageUrl = trip.imageUrl,
                    size = 80.dp,
                    cornerRadius = 12.dp
                )

                Spacer(Modifier.width(14.dp))

                // Trip Info Column
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
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

                    Spacer(Modifier.width(8.dp))

                    // Members count row
                    IconLabelRow(
                        icon = Icons.Rounded.People,
                        label = "${trip.membersCount} Members"
                    )

                    Spacer(Modifier.height(4.dp))

                    // Location row
                    IconLabelRow(
                        icon = Icons.Rounded.LocationOn,
                        label = trip.location.orEmpty()
                    )
                }

                Spacer(Modifier.width(8.dp))

                // Chevron arrow button
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Rounded.ChevronRight, null,
                        tint     = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }

            // Divider
            HorizontalDivider(
                color     = MaterialTheme.colorScheme.outlineVariant,
                thickness = 0.8.dp,
            )

            // Status Footer Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PulsingDot()
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text  = trip.status.orEmpty(),
                        style = WayflockTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                VerticalDivider(
                    modifier = Modifier.height(14.dp),
                    color = MaterialTheme.colorScheme.outlineVariant,
                    thickness = 0.8.dp,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text  = "Last updated: ${trip.lastUpdated}",
                        style = WayflockTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        Icons.Rounded.SignalCellularAlt, null,
                        tint     = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(14.dp),
                    )
                }
            }
        }
    }
}

// Recent Trip Item
@Composable
fun RecentTripItem(
    trip:     Trip,
    onClick:  () -> Unit,
    modifier: Modifier = Modifier,
){
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = CardShapeDefault,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier          = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Thumbnail — TripThumbnail is SHARED from ui/components/
            TripThumbnail(
                imageUrl     = trip.imageUrl,
                size         = 58.dp,
                cornerRadius = 10.dp,
            )

            Spacer(Modifier.width(14.dp))

            // Trip name + date
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = trip.name,
                    style      = WayflockTypography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color      = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(4.dp))
                IconLabelRow(
                    icon      = Icons.Rounded.CalendarToday,
                    label     = trip.dateRange.orEmpty(),
                    iconSize  = 13.dp,
                    textStyle = WayflockTypography.bodySmall,
                )
            }

            Spacer(Modifier.width(8.dp))

            // Status badge — TripStatusBadge is SHARED from ui/components/
            if (trip.isCompleted) {
                TripStatusBadge(status = TripDisplayStatus.COMPLETED)
            }
        }
    }
}

// Live Badge
@Composable
fun LiveBadge(
    modifier: Modifier = Modifier,
){
    Surface(
        modifier = modifier,
        shape = BadgeShape,
        color = BadgeLiveBackground
    ) {
        Text(
            text = "LIVE",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            style = LiveBadgeText,
            color = BadgeLiveText,
        )
    }
}

// Pulsing Dot
@Composable
fun PulsingDot(
    modifier: Modifier = Modifier
){
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation  = tween(700, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "dotScale"
    )
    Box(
        modifier = Modifier
            .size(9.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(OnlineDot)
    )
}

// Icon label row
@Composable
fun IconLabelRow(
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    iconSize: androidx.compose.ui.unit.Dp = 15.dp,
    textStyle: androidx.compose.ui.text.TextStyle = WayflockTypography.bodyMedium,
){
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


// Sample Data
private val sampleActiveTrip = Trip(
    id          = "1",
    name        = "Manali Trip",
    location    = "Manali, Himachal Pradesh",
    membersCount= 5,
    imageUrl    = "https://images.unsplash.com/photo-1626621341517-bbf3d9990a23?w=400&q=80",
    isLive      = true,
    status      = "All members are online",
    lastUpdated = "Just now",
)

private val sampleRecentTrip = listOf(
    Trip(
        id = "2",
        name = "Goa Trip",
        imageUrl = "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?w=200&q=80",
        dateRange = "10–15 May 2024",
        isCompleted = true,
    ),
    Trip(
        id        = "3",
        name      = "Rishikesh Trip",
        imageUrl  = "https://images.unsplash.com/photo-1566438480900-0609be27a4be?w=200&q=80",
        dateRange = "22–24 Apr 2024",
        isCompleted = true,
    ),
    Trip(
        id        = "4",
        name      = "Kasol Trip",
        imageUrl  = "https://images.unsplash.com/photo-1605649440416-43f942f6f1a9?w=200&q=80",
        dateRange = "5–7 Apr 2024",
        isCompleted = true,
    ),
)

@Preview(showSystemUi = true, name = "Home — Light")
@Composable
private fun HomeScreenLightPreview() {
    WayflockTheme(darkTheme = false) {
        HomeScreen()
    }
}
