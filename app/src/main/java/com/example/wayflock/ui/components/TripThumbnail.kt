package com.example.wayflock.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun TripThumbnail(
    imageUrl : String?,
    modifier: Modifier = Modifier,
    size : Dp = 60.dp,
    cornerRadius : Dp = 12.dp
){

    val shape = RoundedCornerShape(cornerRadius)

    AsyncImage(
        model = imageUrl?.let { url ->
            if (url.contains("unsplash.com") && !url.contains("?")) "$url?w=400&q=80"
            else url
        },
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant, shape)
    )

}