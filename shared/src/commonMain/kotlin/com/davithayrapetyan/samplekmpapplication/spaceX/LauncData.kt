package com.davithayrapetyan.samplekmpapplication.spaceX

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
    @SerialName("rocket_name") val rocketName: String
)

@Serializable
data class LaunchData(
    @SerialName("flight_number") val flightNumber: Int,
    @SerialName("mission_name") val missionName: String,
    @SerialName("launch_date_utc") val launchDate: String,
    val rocket: Rocket
)