package com.thebluealliance.androidclient.models.kotlin.eventdetail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RankingItemKotlin(
    @Json(name = "matches_played") val matchesPlayed: Int,
    val dq: Int,
    val rank: Int,
    @Json(name = "sort_orders") val sortOrders: List<Double>,
    @Json(name = "extra_stats") val extraStats: List<Double>,
    @Json(name = "team_key") val teamKey: String,
    val record: TeamRecordKotlin,
    @Json(name = "qual_average") val qualAverage: Double?,
)
