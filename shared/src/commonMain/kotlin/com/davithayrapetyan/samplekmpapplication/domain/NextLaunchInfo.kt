package com.davithayrapetyan.samplekmpapplication.domain

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class NextLaunchInfo(
    val flightNumber: String,
    val missionName: String,
    val launchDate: Instant,
    val rocketName: String
)