package nl.joerihofman.kotlinmusicplayer.collector.mixcloud

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.get
import io.ktor.http.HeadersBuilder
import io.ktor.http.ParametersBuilder
import io.ktor.util.StringValues
import io.ktor.util.appendAll
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import nl.joerihofman.kotlinmusicplayer.Song
import nl.joerihofman.kotlinmusicplayer.collector.MusicCollector
import nl.joerihofman.kotlinmusicplayer.collector.httpClient
import kotlin.math.log

class MixcloudCollector : MusicCollector {

    companion object {
        private val logger = KotlinLogging.logger { }
        private const val URL = "https://api.mixcloud.com/"
        private val client = httpClient
    }

    override fun getMusic(): List<Song> {

        runBlocking {
            launch {
                collectFromUrl()
            }
        }

        return emptyList()
    }

    private suspend fun collectFromUrl() {
//        val searchUrl = URL + "search/?q=worldwide+fm&type=user"
        val searchUrl = "https://api.mixcloud.com/spartacus/?metadata=1"

        val headers = HeadersBuilder()
        headers.append("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:71.0) Gecko/20100101 Firefox/71.0")
        headers.append("Cookie", "__cfduid=dc6fc0a8aace2851d93e32c24014a465e1573131372; mx_t=370ac1b2-d998-4580-ac4b-aa9aa5ce9091; csrftoken=IwqIgQCoR9Lu0l8ITXuOOj3ImaOxlci3VdjcWY9lpkSCwVicU6tJA7VaDeHNSUNn; c=5gjl37b6y6jez4vf9z9jcmcw5rruv0n5")

        val parameters = ParametersBuilder()
        parameters.append("q", "worldwide+fm")
        parameters.append("type", "user")

        val httpRequest = HttpRequestBuilder()
        httpRequest.headers.appendAll(headers)
        httpRequest.url.host = URL
        httpRequest.url.parameters.appendAll(parameters)

        val response = client.get<HttpResponseData>(httpRequest)
        logger.info("RESP HEADER: ${response.headers}")
        logger.info("RESP BODY: ${response.body}")
    }
}