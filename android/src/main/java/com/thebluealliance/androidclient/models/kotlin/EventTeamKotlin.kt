package com.thebluealliance.androidclient.models.kotlin

import com.thebluealliance.androidclient.models.kotlin.eventdetail.TeamAtEventStatusKotlin

data class EventTeamKotlin(
    val key: String,
    val teamKey: String,
    val eventKey: String,
    val year: Int,
    val status: TeamAtEventStatusKotlin?,
    val lastModified: Long?,
)
