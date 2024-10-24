package com.davithayrapetyan.samplekmpapplication.spaceX

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchData(
    @SerialName("flight_number") val flightNumber: Int,
    @SerialName("name") val missionName: String,
    @SerialName("date_utc") val launchDate: String,
    @SerialName("rocket") val rocket: String // Change from Rocket object to String as per the received JSON
)
