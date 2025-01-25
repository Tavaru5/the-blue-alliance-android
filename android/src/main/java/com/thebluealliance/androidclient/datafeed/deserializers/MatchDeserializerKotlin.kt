package com.thebluealliance.androidclient.datafeed.deserializers


import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import com.thebluealliance.androidclient.helpers.MatchHelper
import com.thebluealliance.androidclient.models.Alliance
import com.thebluealliance.androidclient.models.MatchAlliancesContainerKotlin
import com.thebluealliance.androidclient.models.MatchKotlin
import com.thebluealliance.androidclient.models.MatchKotlin.Companion.getMatchYear
import com.thebluealliance.androidclient.models.MatchScoreBreakdown
import com.thebluealliance.androidclient.models.MatchVideo
import com.thebluealliance.androidclient.models.ScoreBreakdown

@JsonClass(generateAdapter = true)
data class MatchJson(
    val key: String,
    val comp_level: String,
    val match_number: Int,
    val set_number: Int,
    val winning_alliance: String?, // Would be nice to be alliance enum instead,
    // but we need custom adapter for case insensitive enums.
    val time: Long,
    val score_breakdown: MatchScoreBreakdownJson,
    val alliances: MatchAlliancesContainerKotlin,
    val videos: List<MatchVideo>,
)

class MatchAdapter {
    @FromJson fun matchFromJson(
        matchJson: MatchJson
    ): MatchKotlin {
        return MatchKotlin(
            key = matchJson.key,
            eventKey = MatchHelper.getEventKeyFromMatchKey(matchJson.key),
            compLevel = matchJson.comp_level,
            matchNumber = matchJson.match_number,
            setNumber = matchJson.set_number,
            winningAlliance = Alliance.fromString(matchJson.winning_alliance),
            time = matchJson.time,
            videos = matchJson.videos,
            alliances = matchJson.alliances,
            scoreBreakdown = matchJson.score_breakdown.toDomain(matchJson.key.getMatchYear())
        )
    }

    @ToJson fun matchToJson(match: MatchKotlin): MatchJson {
        return MatchJson(
            key = match.key,
            comp_level = match.compLevel,
            match_number = match.matchNumber,
            set_number = match.setNumber,
            winning_alliance = match.winningAlliance?.toString(),
            time = match.time,
            videos = match.videos,
            alliances = match.alliances,
            score_breakdown = MatchScoreBreakdownJson.fromDomain(match.scoreBreakdown, match
                .getYear())
        )
    }
}

@JsonClass(generateAdapter = true)
data class MatchScoreBreakdownJson(
    val red: ScoreBreakdownJson,
    val blue: ScoreBreakdownJson
) {
    fun toDomain(year: Int) = MatchScoreBreakdown(
            red = red.toDomain(year),
            blue = blue.toDomain(year),
        )

    companion object {
        fun fromDomain(matchScoreBreakdown: MatchScoreBreakdown, year: Int) = MatchScoreBreakdownJson(
            red = ScoreBreakdownJson.fromDomain(matchScoreBreakdown.red, year),
            blue = ScoreBreakdownJson.fromDomain(matchScoreBreakdown.blue, year),
        )
    }
}

@JsonClass(generateAdapter = true)
data class ScoreBreakdownJson(
    val teleopDefensesBreached: Boolean = false,
    val teleopTowerCaptured: Boolean = false,
    val kPaRankingPointAchieved: Boolean = false,
    val rotorRankingPointAchieved: Boolean = false,
    val autoQuestRankingPoint: Boolean = false,
    val faceTheBossRankingPoint: Boolean = false,
    val habDockingRankingPoint: Boolean = false,
    val completeRocketRankingPoint: Boolean = false,
    val shieldOperationalRankingPoint: Boolean = false,
    val shieldEnergizedRankingPoint: Boolean = false,
) {
    fun toDomain(year: Int): ScoreBreakdown = when (year) {
            2016 -> ScoreBreakdown(
                teleopDefensesBreached,
                teleopTowerCaptured
            )
            2017 -> ScoreBreakdown(
                kPaRankingPointAchieved,
                rotorRankingPointAchieved
            )
            2018 -> ScoreBreakdown(
                autoQuestRankingPoint,
                faceTheBossRankingPoint
            )
            2019 -> ScoreBreakdown(
                habDockingRankingPoint,
                completeRocketRankingPoint
            )
            2020 -> ScoreBreakdown(
                shieldOperationalRankingPoint,
                shieldEnergizedRankingPoint
            )
            else -> ScoreBreakdown(false, false)
    }

    companion object {
        fun fromDomain(scoreBreakdown: ScoreBreakdown, year: Int): ScoreBreakdownJson = when (year) {
            2016 -> ScoreBreakdownJson(
                teleopDefensesBreached = scoreBreakdown.rp1,
                teleopTowerCaptured = scoreBreakdown.rp2,
            )
            2017 -> ScoreBreakdownJson(
                kPaRankingPointAchieved = scoreBreakdown.rp1,
                rotorRankingPointAchieved = scoreBreakdown.rp2,
            )
            2018 -> ScoreBreakdownJson(
                autoQuestRankingPoint = scoreBreakdown.rp1,
                faceTheBossRankingPoint = scoreBreakdown.rp2,
            )
            2019 -> ScoreBreakdownJson(
                habDockingRankingPoint = scoreBreakdown.rp1,
                completeRocketRankingPoint = scoreBreakdown.rp2,
            )
            2020 -> ScoreBreakdownJson(
                shieldOperationalRankingPoint = scoreBreakdown.rp1,
                shieldEnergizedRankingPoint = scoreBreakdown.rp2,
            )
            else -> ScoreBreakdownJson()
        }
    }
}

