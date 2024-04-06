package io.github.t45k.ghcbonk.twitter.tweetModel

import io.github.t45k.ghcbonk.github.ContributionCount
import io.github.t45k.ghcbonk.util.JstDate
import java.time.format.DateTimeFormatter

class WordleLikeTweetModel : TweetModel {
    companion object {
        private const val NO_ACTIVITY = "⬛"
        private const val ACTIVITY = "🟩"
        private val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    }

    override fun getContent(yesterday: JstDate, contributionCounts: List<ContributionCount>): String {
        val contributionCountsUntilYesterday = contributionCounts.takeWhile { it.date <= yesterday }
        val mostLastContributionCounts = contributionCountsUntilYesterday.takeLast(28 + yesterday.dayOfWeek.value)
        val actives = mostLastContributionCounts.count { it.count > 0 }
        return """
            |GitHub ${yesterday.format(formatter)} $actives/${mostLastContributionCounts.count()}
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
