package com.thebluealliance.androidclient.models.kotlin.eventdetail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class EventAllianceKotlin(
    val picks: List<String>,
    val eventKey: String?,
    val declines: List<String>,
    val name: String?,
    val backup: AllianceBackupKotlin?,
    val status: TeamAtEventPlayoffKotlin?,
    val lastModified: Long?,
)

@JsonClass(generateAdapter = true)
data class AllianceBackupKotlin(
    @Json(name = "in") val teamIn: String?,
    @Json(name = "out") val teamOut: String?
)