package com.example.wayflock.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wayflock.ui.theme.ActionLinkText
import com.example.wayflock.ui.theme.Green500
import com.example.wayflock.ui.theme.SectionHeaderText


@Composable
fun SectionHeader(
    title : String,
    actionLabel : String,
    onActionClick : () -> Unit,
    modifier: Modifier = Modifier,
    showMapIcon: Boolean = false,
){

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = SectionHeaderText
        )

        TextButton(
            onClick = onActionClick,
            contentPadding = PaddingValues(0.dp),
        ) {
            Text(
                text = actionLabel,
                style = ActionLinkText,
            )

            if (showMapIcon) {
                Spacer(Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Rounded.Map,
                    contentDescription = null,
                    tint = Green500,
                    modifier = modifier.size(16.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SectionHeaderPreview(){
    SectionHeader(
        title = "Active Trip",
        actionLabel = "View on Map",
        onActionClick = { /*TODO*/ },
        showMapIcon = true
    )
}