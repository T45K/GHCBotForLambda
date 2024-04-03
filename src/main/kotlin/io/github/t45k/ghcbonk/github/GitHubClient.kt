package io.github.t45k.ghcbonk.github

import io.github.projectmapk.jackson.module.kogera.jacksonObjectMapper
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.time.LocalDate

class GitHubClient(private val personalAccessToken: String) {
    companion object {
        private const val URL = "https://api.github.com/graphql"
    }

    private val okHttpClient = OkHttpClient()
    private val objectMapper = jacksonObjectMapper()

    fun fetchContributionCounts(user: GitHubUser): List<ContributionCount> {
        val graphQLQuery = """
            {
              user(login: "${user.name}"){
                contributionsCollection {
                  contributionCalendar {
                    weeks {
                      contributionDays {
                        contributionCount
                        date
                      }
                    }
                  }
                }
              }
            }
        """.trimIndent()

        val requestBody = objectMapper.writeValueAsString(mapOf("query" to graphQLQuery))
            .toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url(URL)
            .header("Authorization", "Bearer $personalAccessToken")
            .post(requestBody)
            .build()

        return okHttpClient.newCall(request)
            .execute()
            .body
            .string()
            .let(objectMapper::readTree)["data"]["user"]["contributionsCollection"]["contributionCalendar"]["weeks"]
            .flatMap { weeks ->
                weeks["contributionDays"].map {
                    ContributionCount(
                        LocalDate.parse(it["date"].textValue()),
                        it["contributionCount"].numberValue().toInt()
                    )
                }
            }
    }
}
