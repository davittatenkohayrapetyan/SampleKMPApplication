package com.davithayrapetyan.samplekmpapplication.spaceX

import com.davithayrapetyan.samplekmpapplication.domain.NextLaunchInfo
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json

class SpaceXParser {
    fun parseLaunchData(jsonString: String): NextLaunchInfo {
        // Deserialize the JSON string into LaunchData class
        val launchData = Json.decodeFromString<LaunchData>(jsonString)

        // Convert the launchDate to Instant
        val launchDateInstant = Instant.parse(launchData.launchDate)

        // Return the parsed data as NextLaunchInfo
        return NextLaunchInfo(
            flightNumber = launchData.flightNumber.toString(),
            missionName = launchData.missionName,
            launchDate = launchDateInstant,
            rocketName = launchData.rocket.rocketName
        )
    }
}