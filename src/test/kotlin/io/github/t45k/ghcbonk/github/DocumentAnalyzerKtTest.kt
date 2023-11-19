package io.github.t45k.ghcbonk.github

import org.jsoup.Jsoup
import java.time.ZoneOffset
import java.time.ZonedDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class DocumentAnalyzerKtTest {
    @Test
    fun testAnalyze() {
        val document = Jsoup.connect("https://github.com/T45K").get()
        val contributionCounts = analyzeDocument(document)

        val todayOnUTC = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).toLocalDate()
        assertTrue(contributionCounts.isNotEmpty())
        assertEquals(contributionCounts.last().date, todayOnUTC)
        assertTrue(contributionCounts.last().count >= 0)
    }
}
