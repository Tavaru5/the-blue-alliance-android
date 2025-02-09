package com.thebluealliance.androidclient.models.kotlin.eventdetail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamAtEventStatusKotlin(
    @Json(name = "alliance_status_str") val allianceStatusStr: String,
    @Json(name = "overall_status_str") val overallStatusStr: String,
    @Json(name = "playoff_status_str") val playoffStatusStr: String,
    @Json(name = "alliance") val teamAtEventAlliance: TeamAtEventAlliance,
    val playoff: TeamAtEventPlayoffKotlin,
    val qual: TeamAtEventQual,
    val lastModified: Long? = null,
)

@JsonClass(generateAdapter = true)
data class TeamAtEventQual(
    val ranking: RankingItemKotlin,
    @Json(name = "sort_order_info") val sortOrderInfo: List<RankingSortOrder>,
    @Json(name = "num_teams") val numTeams: Int,
    val status: String,
)

@JsonClass(generateAdapter = true)
data class TeamAtEventAlliance(
    val name: String,
    val number: Int,
    val pick: Int,
    val backup: AllianceBackupKotlin
)

@JsonClass(generateAdapter = true)
data class TeamAtEventPlayoffKotlin(
    val level: String,
    val status: String,
    @Json(name = "playoff_average") val playoffAverage: Double,
    @Json(name = "current_level_record") val currentLevelRecord: TeamRecordKotlin,
    val record: TeamRecordKotlin
)
