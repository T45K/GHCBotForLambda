package io.github.t45k.ghcbonk.twitter

import java.text.SimpleDateFormat
import java.util.Date
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TweetModelTest {

    @Test
    fun testGetContent() {
        val content = TweetModel("hoge", 0, 0).getContent()
        val format = SimpleDateFormat("yyyy年MM月dd日")
        assertEquals(
            """
                ${format.format(Date())}のコントリビューション数: 0
                連続コントリビューション日数: 0
                https://github.com/hoge
            """.trimIndent(),
            content
        )

        assertEquals(3, content.split("\n").size)
    }
}
