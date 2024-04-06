package io.github.t45k.ghcbonk.github

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

        val todayOnUTC = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).toLocalDate()
        assertTrue(contributionCounts.isNotEmpty())
        assertEquals(contributionCounts.last().date, todayOnUTC)
        assertTrue(contributionCounts.last().count >= 0)
    }
}
