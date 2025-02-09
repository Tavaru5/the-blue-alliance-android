package com.thebluealliance.androidclient.models.kotlin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thebluealliance.androidclient.types.MediaType

data class MediaKotlin(
    val foreignKey: String,
    val type: MediaType,
    val preferred: Boolean,
    val details: MediaDetails,
)

@JsonClass(generateAdapter = true)
data class MediaDetails(
    val base64Image: String,
    @Json(name = "image_partial") val imagePartial: String?,
)
