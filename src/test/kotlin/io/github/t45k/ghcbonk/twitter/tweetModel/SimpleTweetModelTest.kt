package io.github.t45k.ghcbonk.twitter.tweetModel

import io.github.t45k.ghcbonk.github.ContributionCount
import io.github.t45k.ghcbonk.util.JstDate
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SimpleTweetModelTest {

    @Test
    fun testGetContent() {
        val date = JstDate.of(2022, 1, 1)
        val content = SimpleTweetModel("hoge").getContent(
            date,
            listOf(ContributionCount(date, 100), ContributionCount(date + 1, 0))
        )
        assertEquals(
            """
                2022年1月1日のコントリビューション数: 100
                連続コントリビューション日数: 1
                https://github.com/hoge
            """.trimIndent(),
            content
        )
    }
}
