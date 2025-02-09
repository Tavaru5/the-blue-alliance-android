package com.thebluealliance.androidclient.models.kotlin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DistrictPointBreakdownKotlin(
    @Json(name = "qual_points") val qualPoints: Int = 0,
    @Json(name = "elim_points") val elimPoints: Int = 0,
    @Json(name = "alliance_points") val alliancePoints: Int = 0,
    @Json(name = "award_points") val awardPoints: Int = 0,
    val total: Int = 0,
    @Json(name = "event_key") val eventKey: String,
    @Json(name = "district_cmp") val districtCmp: Boolean,
    // None of this is part of the initial Json, so I don't think it needs to be serialized?
    val teamKey: String? = null,
    val districtKey: String? = null,
    val teamName: String? = null,
    val rank: Int? = null,
)
