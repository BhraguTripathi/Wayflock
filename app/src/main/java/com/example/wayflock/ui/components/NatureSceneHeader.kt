package com.example.wayflock.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.wayflock.R

@Composable
fun NatureSceneHeader(
    modifier: Modifier = Modifier,
    image: Any?,
    height: Dp = 220.dp,
    blurRadius: Dp = 0.dp,
    content: @Composable BoxScope.() -> Unit = {},
) {
//    val isDark = isSystemInDarkTheme()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
    ) {

        // ── 1. Background photo ───────────────────────────────────
        AsyncImage(
            model = image, // Loads the drawable provided by the Caller
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(
                    radiusX = blurRadius,
                    radiusY = blurRadius,
                ),
        )

        // ── 2. Gradient scrim overlay ─────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = lightScrim,
                    )
                ),
        )

        // ── 3. Caller-provided content ────────────────────────────────────
        content()
    }
}

private val lightScrim = arrayOf(
    0.00f to Color(0x00FFFFFF),   // 0% white at top (completely clear)
    0.50f to Color(0x33FFFFFF),   // 20% white at mid (just a hint)
    0.80f to Color(0x80FFFFFF),   // 50% white at 80%
    1.00f to Color(0xE6FFFFFF),   // 90% white at bottom (blends with screen)
)

private val darkScrim = arrayOf(
    0.00f to Color(0x00000000),   // 0% black at top (completely clear)
    0.50f to Color(0x33000000),   // 20% black at mid
    0.80f to Color(0x80101810),   // 50% dark at 80%
    1.00f to Color(0xE6101810),   // 90% dark at bottom
)

@Preview(showSystemUi = true)
@Composable
fun NatureSceneHeaderPreview() {
    NatureSceneHeader(
        image = R.drawable.homesectionheader
    )
}