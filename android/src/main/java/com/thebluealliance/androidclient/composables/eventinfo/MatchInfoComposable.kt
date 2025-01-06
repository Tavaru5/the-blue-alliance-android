package com.thebluealliance.androidclient.composables.eventinfo

import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thebluealliance.androidclient.R

@Composable
fun MatchInfoComposable() {
    Row(
        modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "Quarter 1-1",
                    textAlign = TextAlign.Center
                )
            }
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Outlined.PlayCircle,
                contentDescription = null,
                tint = colorResource(R.color.primary_text_color)
            )
        }
        Column(modifier = Modifier.weight(2f)) {
            HeaderText(stringResource(R.string.teams))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TeamText("Red 1", R.color.match_view_red_team)
                TeamText("Red 2", R.color.match_view_red_team)
                TeamText("Red 3", R.color.match_view_red_team)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TeamText("Blue 1", R.color.match_view_blue_team)
                TeamText("Blue 2", R.color.match_view_blue_team)
                TeamText("Blue 3", R.color.match_view_blue_team)
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            HeaderText(stringResource(R.string.score))
            ScoreText("Red Score", R.color.match_view_red_score)
            ScoreText("Blue Score", R.color.match_view_blue_score)
        }
        Column(modifier = Modifier.weight(1f)) {
            HeaderText(stringResource(R.string.time))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("88:88 PM")
            }
        }
    }
}

@Composable
fun HeaderText(text: String) = Box(
    modifier = Modifier
        .height(32.dp)
        .fillMaxWidth()
        .background(color = colorResource(R.color.column_header_gray)),
    contentAlignment = Alignment.Center,
) {
    Text(
        text = text,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ScoreText(text: String, @ColorRes backgroundColor: Int) = Box(
    modifier = Modifier
        .height(40.dp)
        .fillMaxWidth()
        .background(color = colorResource(backgroundColor)),
    contentAlignment = Alignment.Center,
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun RowScope.TeamText(text: String, @ColorRes backgroundColor: Int) = Box(
    modifier = Modifier
        .weight(1f)
        .fillMaxHeight()
        .background(color = colorResource(backgroundColor)),
    contentAlignment = Alignment.Center,
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
    )
}
