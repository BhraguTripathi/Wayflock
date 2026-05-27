package com.example.wayflock.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.wayflock.ui.theme.OnlineDot

@Composable
fun UserAvatarWithDot(
    imageURL: String?,
    modifier: Modifier = Modifier,
    avatarSize: Dp = 48.dp,
    dotSize: Dp = 13.dp,
    showOnlineDot: Boolean = true,
){
    Box(
        modifier = modifier
    ){

        //Profile Photo
        AsyncImage(
            model = imageURL,
            contentDescription = "Profile Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(avatarSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape),
        )

        //Online Indicator
        if (showOnlineDot){
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .align(Alignment.BottomEnd)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.surface,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .background(OnlineDot)
            ) { }
        }
    }
}