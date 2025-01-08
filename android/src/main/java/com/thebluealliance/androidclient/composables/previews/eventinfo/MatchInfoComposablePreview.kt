package com.thebluealliance.androidclient.composables.previews.eventinfo

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.thebluealliance.androidclient.composables.eventinfo.MatchInfoComposable
import com.thebluealliance.androidclient.models.Match
import com.thebluealliance.androidclient.models.MatchAlliancesContainer
import com.thebluealliance.androidclient.models.MatchAlliancesContainer.MatchAlliance

@Preview(showBackground = true)
@Composable
fun PreviewBlueMatchWin() {
    val mockMatch = Match().apply {
        matchNumber = 23
        setNumber = 1
        winningAlliance = "blue"
        key = "_qm"
        alliances = MatchAlliancesContainer().apply {
            red = MatchAlliance().apply {
                score = 20
                teamKeys = listOf("1", "2", "6543")
            }
            blue = MatchAlliance().apply {
                score = 21
                teamKeys = listOf("3", "4", "3456")
            }
        }
    }
    MatchInfoComposable(mockMatch)
}

@Preview(showBackground = true)
@Composable
fun PreviewUpcomingMatch() {
    val mockMatch = Match().apply {
        matchNumber = 23
        setNumber = 1
        key = "_qm"
        time = 1736358960L
        alliances = MatchAlliancesContainer().apply {
            red = MatchAlliance().apply {
                teamKeys = listOf("1", "2", "6543")
            }
            blue = MatchAlliance().apply {
                teamKeys = listOf("3", "4", "3456")
            }
        }
    }
    MatchInfoComposable(mockMatch)
}