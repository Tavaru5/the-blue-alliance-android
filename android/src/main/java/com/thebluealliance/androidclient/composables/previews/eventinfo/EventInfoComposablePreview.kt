package com.thebluealliance.androidclient.composables.previews.eventinfo

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.thebluealliance.androidclient.binders.EventInfoBinder
import com.thebluealliance.androidclient.composables.eventinfo.EventInfoComposable

@Preview(showBackground = true)
@Composable
fun EventInfoComposablePreview() {
    val mockEventInfoModel = EventInfoBinder.Model()
    mockEventInfoModel.nameString = "Event Name Goes Here"
    mockEventInfoModel.dateString = "Event Date Goes Here"
    mockEventInfoModel.venueString = "Event Venue Goes Here"
    mockEventInfoModel.webcasts = JsonArray()
    val webcast = JsonObject().apply {
        addProperty("type", "twitch")
    }
    mockEventInfoModel.webcasts.add(webcast)
    mockEventInfoModel.isLive = true
    EventInfoComposable(
        eventInfo = mockEventInfoModel,
        eventName = "Event Name Goes Here",
        eventDate = "Event Date Goes Here",
        eventVenue = "Event Venue Goes Here",
        webcastTitle = "Watch on Twitch",
    )
}