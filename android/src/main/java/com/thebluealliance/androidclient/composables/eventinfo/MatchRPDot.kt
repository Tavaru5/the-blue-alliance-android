package com.thebluealliance.androidclient.composables.eventinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.thebluealliance.androidclient.R

@Composable
fun MatchRPDot() {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(6.dp)
            .background(
                color = colorResource(R.color.primary_text_color),
                shape = CircleShape
            )
    )
}