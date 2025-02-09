package com.thebluealliance.androidclient.models.kotlin

import com.thebluealliance.androidclient.helpers.EventHelper
import com.thebluealliance.androidclient.types.WebcastType
import java.util.Date

data class EventKotlin(
    val key: String,
    val name: String,
    val week: Int?,
    val eventType: Int,
    val shortName: String?,
    val address: String?,
    val district: DistrictKotlin?, // districtKey can just be gotten from this
    val locationName: String?,
    val location: Location?,
    val webcasts: List<Webcast>,
    val website: String?,
    val endDate: Date?,
    val startDate: Date?,
    val lastModified: Long? = null,
) {
    fun getYear(): Int = EventHelper.getYear(key)
    fun getEventCode(): String = EventHelper.getEventCode(key)
}

data class Webcast(
    val type: WebcastType,
    val channel: String?,
    val file: String?,
)
