package com.thebluealliance.androidclient.models.kotlin.eventdetail

import com.thebluealliance.androidclient.types.EventDetailType

data class EventDetailKotlin(
    val key: String,
    val eventKey: String,
    val type: EventDetailType,
    val lastModified: Long?,
    val alliances: List<EventAllianceKotlin>,
    val rankingResponse: RankingResponseKotlin,
)
