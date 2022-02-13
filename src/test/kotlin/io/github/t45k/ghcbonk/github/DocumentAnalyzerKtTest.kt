package io.github.t45k.ghcbonk.github

import org.jsoup.Jsoup
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class DocumentAnalyzerKtTest {
    @Test
    fun testAnalyze() {
        val document = Jsoup.connect("https://github.com/T45K").get()
        val contributionCounts = analyzeDocument(document)

        assertEquals(contributionCounts.last().date, LocalDate.now())
        assertTrue(contributionCounts.last().count >= 0)
    }
}
