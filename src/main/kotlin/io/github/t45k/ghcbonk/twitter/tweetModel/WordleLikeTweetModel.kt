package io.github.t45k.ghcbonk.twitter.tweetModel

import io.github.t45k.ghcbonk.github.ContributionCount
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WordleLikeTweetModel : TweetModel {
    companion object {
        private const val NO_ACTIVITY = "⬛"
        private const val ACTIVITY = "🟩"
        private val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    }

    override fun getContent(today: LocalDate, contributionCounts: List<ContributionCount>): String {
        val mostLastContributionCounts = contributionCounts.takeLast(28 + today.dayOfWeek.value)
        val actives = mostLastContributionCounts.count { it.count > 0 }
        return """
            |GitHub ${formatter.format(today)} $actives/${mostLastContributionCounts.count()}
            |${convertWordleStyle(mostLastContributionCounts)}
        """.trimMargin()
    }

    private fun convertWordleStyle(contributionCounts: List<ContributionCount>): String =
        (0 until 7).joinToString("\n") { i ->
            generateSequence(i) { it + 7 }
                .takeWhile { it < contributionCounts.size }
                .joinToString("") { if (contributionCounts[it].count > 0) ACTIVITY else NO_ACTIVITY }
        }
}
