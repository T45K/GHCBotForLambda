package t45k.ghcbonk.github

import org.jsoup.Jsoup
import kotlin.test.Test
import kotlin.test.assertTrue

internal class DocumentAnalyzerKtTest {
    @Test
    fun testAnalyze() {
        val document = Jsoup.connect("https://github.com/T45K").get()
        val (contributionCount, contributionStreak) = analyzeDocument(document)
        assertTrue(contributionCount >= 0)
        assertTrue(contributionStreak >= 0)
    }
}