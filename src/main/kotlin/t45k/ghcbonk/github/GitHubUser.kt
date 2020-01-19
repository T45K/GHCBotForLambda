package t45k.ghcbonk.github

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.streams.toList

class GitHubUser(private val userName: String) {
    companion object {
        private const val USER_URL_PREFIX = "https://github.com/"
    }

    private val userURL = URL(USER_URL_PREFIX + userName)

    fun fetchContributionData(): ContributionData {
        val connection: HttpURLConnection = setConnection()
        val rawData: List<String> = fetchHTMLSource(connection)

        return analyzeRawData(rawData, userName)
    }

    private fun setConnection(): HttpURLConnection {
        val connection: HttpURLConnection = userURL.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.instanceFollowRedirects = false
        connection.connect()

        return connection
    }

    private fun fetchHTMLSource(connection: HttpURLConnection): List<String> {
        val status: Int = connection.responseCode
        return if (status == HttpURLConnection.HTTP_OK) {
            readInputStream(connection.inputStream)
        } else {
            emptyList()
        }
    }

    private fun readInputStream(inputStream: InputStream): List<String> {
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader as Reader)
        return bufferedReader.lines()
                .filter { isNecessaryInformation(it) }
                .toList()
    }

    private fun isNecessaryInformation(line: String): Boolean {
        return line.contains("<rect")
    }
}
