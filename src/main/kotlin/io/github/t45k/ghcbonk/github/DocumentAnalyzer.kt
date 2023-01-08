package io.github.t45k.ghcbonk.github

import org.jsoup.nodes.Document
import java.time.LocalDate

private const val CONTRIBUTION_CALENDAR_CSS_NAME = "ContributionCalendar-day"
private const val DATE_ATTR_NAME = "data-date"

fun analyzeDocument(document: Document): List<ContributionCount> =
    document.select(".$CONTRIBUTION_CALENDAR_CSS_NAME")
        .filter { it.hasAttr(DATE_ATTR_NAME) }
        .sortedBy { it.attr(DATE_ATTR_NAME) }
        .map { LocalDate.parse(it.attr(DATE_ATTR_NAME)) to parseCalendarMessage(it.text()) }

/**
 * @param message like "1 contribution on January 9, 2023" or "No contribution on January 9, 2023"
 */
private fun parseCalendarMessage(message: String): Int = message.split(" ").first().toIntOrNull() ?: 0
