package io.github.t45k.ghcbonk.github

import io.github.t45k.ghcbonk.util.JstDate
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class GitHubClient(private val personalAccessToken: String) {
    companion object {
        private const val URL = "https://api.github.com/graphql"
    }

    fun fetchContributionCounts(user: GitHubUser): List<ContributionCount> {
        val graphQLQuery = """
            {
              user(login: \"${user.name}\") {
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
        """.replace("\n", " ")
        val requestBody = """{"query": "$graphQLQuery"}"""

        val responseBody = HttpClient.newHttpClient().use { httpClient ->
            httpClient.send(
                HttpRequest.newBuilder(URI.create(URL))
                    .header("Authorization", "Bearer $personalAccessToken")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            ).body()
        }!!

        return """"contributionCount":(\d+),"date":"(\d{4})-(\d{2})-(\d{2})"""".toRegex()
            .findAll(responseBody)
            .map { it.groupValues }
            .map { (_, count, year, month, dayOfMonth) ->
                ContributionCount(JstDate.of(year.toInt(), month.toInt(), dayOfMonth.toInt()), count.toInt())
            }.toList()
    }
}
