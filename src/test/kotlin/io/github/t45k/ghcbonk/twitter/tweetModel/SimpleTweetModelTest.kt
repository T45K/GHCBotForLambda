package io.github.t45k.ghcbonk.twitter.tweetModel

import io.github.t45k.ghcbonk.github.to
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SimpleTweetModelTest {

    @Test
    fun testGetContent() {
        val date = LocalDate.of(2022, 1, 1)
        val content = SimpleTweetModel("hoge").getContent(date, listOf(date to 100))
        val format = DateTimeFormatter.ofPattern("yyyy年MM月dd日")
        assertEquals(
            """
                ${format.format(date)}のコントリビューション数: 100
                連続コントリビューション日数: 1
                https://github.com/hoge
            """.trimIndent(),
            content
        )
    }
}
