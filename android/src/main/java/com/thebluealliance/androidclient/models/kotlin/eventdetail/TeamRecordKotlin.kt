package com.thebluealliance.androidclient.models.kotlin.eventdetail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamRecordKotlin(
    val wins: Int,
    val losses: Int,
    val ties: Int,
)
