package com.thebluealliance.androidclient.models.kotlin

import android.content.ContentValues
import com.google.gson.Gson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thebluealliance.androidclient.database.TbaDatabaseModel
import com.thebluealliance.androidclient.helpers.AwardHelper
import com.thebluealliance.androidclient.interfaces.RenderableModel
import com.thebluealliance.androidclient.listitems.ListElement
import com.thebluealliance.androidclient.renderers.ModelRendererSupplier
import com.thebluealliance.androidclient.types.ModelType

// Unsure if these last modifieds need to exist here, they're only accessed when doing db stuff
@JsonClass(generateAdapter = true)
data class AwardKotlin(
    @Json(name = "award_type") val awardType: Int,
    @Json(name = "event_key") val eventKey: String,
    val name: String,
    val year: Int,
    @Json(name = "recipient_list") val recipientList: List<AwardRecipient>
)

@JsonClass(generateAdapter = true)
data class AwardRecipient(
    val awardee: String?,
    @Json(name = "team_key") val teamKey: String,
    val lastModified: Long? = null,
)