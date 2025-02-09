package com.thebluealliance.androidclient.models.kotlin

import com.squareup.moshi.JsonClass

data class MatchKotlin(
    val key: String,
    val eventKey: String,
    val compLevel: String,
    val matchNumber: Int,
    val setNumber: Int,
    val winningAlliance: Alliance?,
    val time: Long,
    val alliances: MatchAlliancesContainerKotlin,
    val scoreBreakdown: MatchScoreBreakdown,
    val videos: List<MatchVideo>,
) {
    fun getYear() = key.getMatchYear()

    companion object {
        fun String.getMatchYear(): Int {
            return (substring(0, 4).toInt())
        }
    }
}

data class MatchScoreBreakdown(
    val red: ScoreBreakdown,
    val blue: ScoreBreakdown,
)

data class ScoreBreakdown(
    val rp1: Boolean,
    val rp2: Boolean
)

// Type should be an enum, specifically MediaType
@JsonClass(generateAdapter = true)
data class MatchVideo(
    val key: String,
    val type: String,
)

enum class Alliance {
    RED,
    BLUE;

    override fun toString(): String = this.name.toLowerCase()

    companion object {
        fun fromString(alliance: String?): Alliance? = when (alliance) {
            "red" -> RED
            "blue" -> BLUE
            else -> null
        }
    }
}
