package com.davithayrapetyan.samplekmpapplication

import com.davithayrapetyan.samplekmpapplication.domain.NextLaunchInfo
import com.davithayrapetyan.samplekmpapplication.spaceX.SpaceXParser
import io.ktor.client.*
import io.ktor.client.engine.cio.* // For JVM/Android
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

// Define SPACEX_URL constant

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual class SpaceXApi {
    actual val client = HttpClient(CIO) {
        engine {
            https {
                // Disable hostname verification
                trustManager = TrustAllCertificates()
            }
        }
    }

    // Trust manager to trust all certificates (for development purposes only)
    class TrustAllCertificates : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate>? = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
    }

    actual suspend fun fetchNextLaunch(): NextLaunchInfo? {
        return try {
            val response: HttpResponse = client.get(SPACEX_URL)
            val spaceXParser = SpaceXParser()
            spaceXParser.parseLaunchData(response.bodyAsText())
        } catch (e: Exception) {
            e.printStackTrace()
            null  // Return null in case of an exception
        }
    }
}
