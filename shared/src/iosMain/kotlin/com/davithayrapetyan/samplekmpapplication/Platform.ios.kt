package com.davithayrapetyan.samplekmpapplication

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import platform.UIKit.UIDevice


class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual class SpaceXApi {
    actual val client = HttpClient(Darwin.create())

    actual suspend fun fetchNextLaunch(): String {

        return try {
            val response = client.get(SPACEX_URL)
            response.bodyAsText() // Correct usage for Ktor 2.0+
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

}

