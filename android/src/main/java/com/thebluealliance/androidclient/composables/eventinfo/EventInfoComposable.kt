package com.thebluealliance.androidclient.composables.eventinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.JsonObject
import com.thebluealliance.androidclient.R
import com.thebluealliance.androidclient.binders.EventInfoBinder
import com.thebluealliance.androidclient.helpers.WebcastHelper

@Composable
fun EventInfoComposable(
    eventInfo: EventInfoBinder.Model,
    eventName: String?,
    eventDate: String?,
    eventVenue: String?,
    webcastTitle: String?,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        ) {
        ElevatedCard(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(4.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                eventName?.let {
                    EventInfoRow(it, Icons.Outlined.Info)
                }
                eventDate?.let {
                    EventInfoRow(it, Icons.Outlined.Event, true)
                }
                eventVenue?.let {
                    EventInfoRow(it, Icons.Filled.Place, true)
                }
                webcastTitle?.let {
//                    val buttonText: String
//                    if (eventInfo.webcasts.size() == 1) {
//                        val eventWebcast: JsonObject = eventInfo.webcasts.get(0).getAsJsonObject()
//                        val webcastType = WebcastHelper.getType(eventWebcast["type"].asString)
//                        buttonText = webcastType.render(LocalContext.current)
//                    } else {
//                        buttonText = stringResource(R.string.view_webcast_button)
//                    }
                    HorizontalDivider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 64.dp))
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = { /* TODO */ },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.primary)
                        )
                    ) {
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = it,
                            fontSize = 14.sp,
                            color = colorResource(R.color.white)
                            )
                    }
                }
            }
        }
    }
}

@Composable
fun EventInfoRow(text: String, icon: ImageVector, showTopDivider: Boolean = false) {
    Column {
        if (showTopDivider) {
            HorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 64.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                modifier = Modifier.size(24.dp),
                contentDescription = "", tint = colorResource(R.color.primary)
            )
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = text,
                fontSize = 16.sp,
                color = colorResource(R.color.primary_text_color)
            )
        }
    }

}