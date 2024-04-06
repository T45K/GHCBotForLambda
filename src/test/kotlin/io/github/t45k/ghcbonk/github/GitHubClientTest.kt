package io.github.t45k.ghcbonk.github

import io.github.t45k.ghcbonk.util.JstDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import java.time.ZoneOffset
import java.time.ZonedDateTime

internal class GitHubClientTest {
    @Test
    fun testFetchContributionCounts() {
        val gitHubClient = GitHubClient("") // write personal access token
        val contributionCounts = gitHubClient.fetchContributionCounts(GitHubUser("T45K"))

        val today = JstDate.from(ZonedDateTime.now(ZoneOffset.UTC))
        assertTrue(contributionCounts.isNotEmpty())
        assertEquals(today, contributionCounts.last().date)
        assertTrue(contributionCounts.last().count >= 0)
    }
}
