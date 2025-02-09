package com.thebluealliance.androidclient.models.kotlin

import com.thebluealliance.androidclient.helpers.kotlin.multiLet

// Separate class, so that we can handle the location templating at the UI layer
data class Location(
    val city: String,
    val stateProv: String,
    val country: String,
) {
    companion object {
        fun fromCityStateCountry(
            city: String?,
            stateProv: String?,
            country: String?
        ) = multiLet(city, stateProv, country) { (first, second, third) ->
            Location(first, second, third)
        }
    }
}

