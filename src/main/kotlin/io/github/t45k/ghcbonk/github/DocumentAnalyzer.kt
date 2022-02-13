package io.github.t45k.ghcbonk.github

import org.jsoup.nodes.Document
import java.time.LocalDate

fun analyzeDocument(document: Document): List<ContributionCount> =
    document.select("rect[data-count]")
        .sortedBy { it.attr("data-date") }
        .map { LocalDate.parse(it.attr("data-date")) to it.attr("data-count").toInt() }
        .takeLast(40)
