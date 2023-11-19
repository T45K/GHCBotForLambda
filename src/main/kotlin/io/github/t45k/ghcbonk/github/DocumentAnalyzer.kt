package io.github.t45k.ghcbonk.github

import org.jsoup.nodes.Document
import java.time.LocalDate

private const val CONTRIBUTION_DAY_COMPONENT_PREFIX = "contribution-day-component"
private const val DATE_ATTR_NAME = "data-date"

fun analyzeDocument(document: Document): List<ContributionCount> {
    val contributionCountByTdId = document.select(buildPrefixSearchCommand("tool-tip", "for", CONTRIBUTION_DAY_COMPONENT_PREFIX))
        .associate { it.attr("for") to parseCalendarMessage(it.text()) }

    return document.select(buildPrefixSearchCommand("td", "id", CONTRIBUTION_DAY_COMPONENT_PREFIX))
        .sortedBy { it.attr(DATE_ATTR_NAME) }
        .map { LocalDate.parse(it.attr(DATE_ATTR_NAME)) to contributionCountByTdId[it.id()]!! }
}

private fun buildPrefixSearchCommand(tagName: String, attrName: String, valuePrefix: String): String =
    """$tagName[$attrName^="$valuePrefix-"]"""

/**
 * @param message like "1 contribution on January 9, 2023" or "No contribution on January 9, 2023"
 */
private fun parseCalendarMessage(message: String): Int = message.split(" ").first().toIntOrNull() ?: 0
