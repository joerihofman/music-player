package nl.joerihofman.kotlinmusicplayer.collector

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import org.apache.http.HttpHost

val httpClient = HttpClient(Apache) {
    engine {
        followRedirects = true
        socketTimeout = 10_000
        connectTimeout = 10_000
        connectionRequestTimeout = 20_000

        customizeClient {
            setProxy(HttpHost("127.0.0.1", 8080))
            setMaxConnTotal(1000)
            setMaxConnPerRoute(100)
        }

        customizeRequest {
        }
    }
}

