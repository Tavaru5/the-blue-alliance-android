package com.thebluealliance.androidclient.models.kotlin.eventdetail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RankingResponseKotlin(
    val rankings: List<RankingItemKotlin>,
    @Json(name = "sort_order_info") val sortOrderInfo: List<RankingSortOrder>,
    @Json(name = "extra_stats_info") val extraStatsInfo: List<RankingSortOrder>,
    val eventKey: String? = null,
    val lastModified: Long? = null,
)

data class RankingSortOrder(
    val name: String,
    val precision: Int,
)
