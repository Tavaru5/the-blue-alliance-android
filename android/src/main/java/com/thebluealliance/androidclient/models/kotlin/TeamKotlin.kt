package com.thebluealliance.androidclient.models.kotlin

data class TeamKotlin(
    val key: String,
    val name: String?,
    val nickname: String?,
    val teamNumber: Int,
    val website: String?,
    val address: String?,
    val gmapsUrl: String?,
    val locationName: String?,
    val location: Location?,
    val motto: String?,
    val lastModified: Long? = null,
    val yearsParticipated: List<Int> = listOf(), // This gets fetched separately
)

