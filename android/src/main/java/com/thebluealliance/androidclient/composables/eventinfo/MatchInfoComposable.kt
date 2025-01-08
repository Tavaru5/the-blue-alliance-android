package com.thebluealliance.androidclient.composables.eventinfo

import android.text.format.DateFormat
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thebluealliance.androidclient.R
import com.thebluealliance.androidclient.models.Match
import com.thebluealliance.androidclient.types.AllianceColor
import java.util.Date
import java.util.Locale

private const val matchTimeFormatSkeleton = "E hh:mm a"

@Composable
fun MatchInfoComposable(match: Match) {
    // make scores null if match hasn't been played
    val showScores =  match.alliances?.red?.score != null && match.alliances?.blue?.score != null
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
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = match.getTitle(LocalContext.current.resources),
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
        Column(
            modifier = Modifier.weight(3f)
        ) {
            // Headers
            Row {
                HeaderText(stringResource(R.string.teams), 2f)
                if (showScores) {
                    HeaderText(stringResource(R.string.score), 1f)
                } else {
                    HeaderText(stringResource(R.string.time), 1f)
                }
            }
            Row {
                // Teams
                Column(modifier = Modifier.weight(2f)) {
                    var redModifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                    var blueModifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                    when (match.winningAllianceColor) {
                        AllianceColor.BLUE -> blueModifier = blueModifier.border(
                            2.dp, colorResource(R.color.blue)
                        )
                        AllianceColor.RED -> redModifier = redModifier.border(
                            2.dp, colorResource(R.color.red)
                        )
                        else -> {}
                    }
                    Row(
                        redModifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        match.alliances?.red?.teamKeys?.forEach { teamKey ->
                            teamKey?.let {
                                TeamText(
                                    it,
                                    R.color.match_view_red_team,
                                    teamKey == match.selectedTeamNumber
                                )
                            }
                        }
                        if (showScores) {
                            ScoreText(
                                match.alliances!!.red.score.toString(),
                                R.color.match_view_red_score
                            )
                        }
                    }
                    Row(
                        blueModifier,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        match.alliances?.blue?.teamKeys?.forEach { teamKey ->
                            teamKey?.let {
                                TeamText(
                                    it,
                                    R.color.match_view_blue_team,
                                    teamKey == match.selectedTeamNumber
                                )
                            }
                        }
                        if (showScores) {
                            ScoreText(
                                match.alliances!!.blue.score.toString(),
                                R.color.match_view_blue_score
                            )
                        }
                    }
                }
                if (!showScores) {
                    Column(modifier = Modifier.weight(1f)) {
                        val localTimeString: String
                        if (match.time == null) {
                            localTimeString = stringResource(R.string.no_time_available)
                        } else {
                            val date = Date(match.time!! * 1000L)
                            val format = DateFormat.getBestDateTimePattern(
                                Locale.getDefault(),
                                matchTimeFormatSkeleton
                            )
                            localTimeString = format.format(date)
                        }
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = localTimeString,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun RowScope.HeaderText(text: String, weight: Float) = Box(
    modifier = Modifier
        .height(32.dp)
        .weight(weight)
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
fun RowScope.ScoreText(text: String, @ColorRes backgroundColor: Int) = Box(
    modifier = Modifier
        .height(40.dp)
        .weight(1.5f)
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
fun RowScope.TeamText(
    text: String,
    @ColorRes backgroundColor: Int,
    isSelectedTeam: Boolean
) = Box(
    modifier = Modifier
        .weight(1f)
        .fillMaxHeight()
        .background(color = colorResource(backgroundColor)),
    contentAlignment = Alignment.Center,
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = if (isSelectedTeam) FontWeight.Bold else FontWeight.Normal
    )
}
