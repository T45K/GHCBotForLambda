package t45k.ghcbonk.twitter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TweetModel(
    private val githubUserName: String,
    private val lastContributionCount: Int,
    private val contributionStreak: Int
) {
    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")
    }

    fun getContent(): String =
        """
            ${LocalDate.now().format(formatter)}のコントリビューション数: $lastContributionCount
            連続コントリビューション日数: $contributionStreak
            https://github.com/$githubUserName
        """.trimIndent()
}
