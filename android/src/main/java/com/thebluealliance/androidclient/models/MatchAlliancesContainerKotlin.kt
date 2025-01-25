package com.thebluealliance.androidclient.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchAlliancesContainerKotlin(
    val red: MatchAlliance,
    val blue: MatchAlliance,
)

@JsonClass(generateAdapter = true)
data class MatchAlliance(
    val score: Int,
    @Json(name = "team_keys")
    val teamKeys: List<String>,
)