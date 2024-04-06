package io.github.t45k.ghcbonk.twitter.tweetModel

import io.github.t45k.ghcbonk.github.ContributionCount
import io.github.t45k.ghcbonk.util.JstDate
import kotlin.test.Test
import kotlin.test.assertEquals

internal class WordleLikeTweetModelTest {

    @Test
    fun testGetContent() {
        val date = JstDate.of(2022, 1, 2) // Sunday
        val contributionCounts = (34 downTo 0).map { ContributionCount(date - it.toLong(), it % 2) }
        val content = WordleLikeTweetModel().getContent(date, contributionCounts)

        assertEquals(
            """
                GitHub 20220102 17/35
                ⬛🟩⬛🟩⬛
                🟩⬛🟩⬛🟩
                ⬛🟩⬛🟩⬛
                🟩⬛🟩⬛🟩
                ⬛🟩⬛🟩⬛
                🟩⬛🟩⬛🟩
                ⬛🟩⬛🟩⬛
            """.trimIndent(),
            content
        )
    }
}
