package io.github.t45k.ghcbonk.twitter.tweetModel

import io.github.t45k.ghcbonk.github.ContributionCount
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SimpleTweetModel(private val githubUserName: String) : TweetModel {
    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")
    }

    override fun getContent(today: LocalDate, contributionCounts: List<ContributionCount>): String {
        val lastContributionCount = contributionCounts.last().count
        val contributionStreak = contributionCounts.takeLastWhile { it.count > 0 }.count()
        return """
            ${today.format(formatter)}のコントリビューション数: $lastContributionCount
            連続コントリビューション日数: $contributionStreak
            https://github.com/$githubUserName
        """.trimIndent()
    }
}
