package com.davithayrapetyan.samplekmpapplication

import com.davithayrapetyan.samplekmpapplication.domain.NextLaunchInfo
import io.ktor.client.HttpClient

interface Platform {
    val name: String
}

const val SPACEX_URL = "https://api.spacexdata.com/v4/launches/next"

expect fun getPlatform(): Platform
expect class SpaceXApi {

    val client: HttpClient

    suspend fun fetchNextLaunch(): NextLaunchInfo?

}