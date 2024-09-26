package com.davithayrapetyan.samplekmpapplication

import io.ktor.client.*
import io.ktor.client.engine.cio.* // For JVM/Android
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual class SpaceXApi {
    actual val client = HttpClient(CIO)
    {
        engine {
            https {
                // Disable hostname verification
                trustManager = TrustAllCertificates()
            }
        }
    }

    class TrustAllCertificates : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate>? = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
    }
    actual suspend fun fetchNextLaunch(): String {
        return try {
            val response = client.get(SPACEX_URL)
            response.bodyAsText() // Correct usage for Ktor 2.0+
        } catch (e: Exception) {
            "Error: ${e.stackTraceToString()}"
        }
    }
}
