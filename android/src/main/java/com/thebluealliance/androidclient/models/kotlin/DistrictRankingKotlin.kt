package com.thebluealliance.androidclient.models.kotlin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Key and districtKey come from elsewhere
@JsonClass(generateAdapter = true)
data class DistrictRankingKotlin(
    @Json(name = "point_total") val pointTotal: Int,
    val rank: Int,
    @Json(name = "rookie_bonus") val rookieBonus: Int,
    @Json(name = "team_key") val teamKey: String,
    @Json(name = "event_points") val eventPoints: List<DistrictPointBreakdownKotlin>,
    val lastModified: Long? = null,
    val districtKey: String? = null,
) {
    fun getKey(): String? = districtKey?.let { "${districtKey}_${teamKey}" }
}
