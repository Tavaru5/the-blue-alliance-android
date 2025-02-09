package com.thebluealliance.androidclient.models.kotlin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DistrictKotlin(
    val key: String,
    val abbreviation: String,
    val year: Int,
    @Json(name = "display_name") val displayName: String,
    val lastModified: Long? = null,
)
