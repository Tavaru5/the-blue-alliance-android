package com.thebluealliance.androidclient.types

enum class AllianceColor(val apiString: String) {
    RED("red"),
    BLUE("blue"),
    NEITHER("");

    companion object {
        fun fromApiString(apiString: String?): AllianceColor = AllianceColor.entries.firstOrNull {
            it.apiString == apiString
        } ?: NEITHER
    }
}