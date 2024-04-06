package io.github.t45k.ghcbonk.twitter.tweetModel

import io.github.t45k.ghcbonk.github.ContributionCount
import io.github.t45k.ghcbonk.util.JstDate
import java.time.format.DateTimeFormatter

class SimpleTweetModel(private val githubUserName: String) : TweetModel {
    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日")
    }

    override fun getContent(yesterday: JstDate, contributionCounts: List<ContributionCount>): String {
        val contributionCountsUntilYesterday = contributionCounts.takeWhile { it.date <= yesterday }
        val lastContributionCount = contributionCountsUntilYesterday.last().count
        val contributionStreak = contributionCountsUntilYesterday.takeLastWhile { it.count > 0 }.count()
        return """
            ${yesterday.format(formatter)}のコントリビューション数: $lastContributionCount
            連続コントリビューション日数: $contributionStreak
            https://github.com/$githubUserName
        """.trimIndent()
    }
}
