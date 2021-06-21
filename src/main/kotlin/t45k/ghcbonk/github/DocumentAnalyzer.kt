package t45k.ghcbonk.github

import org.jsoup.nodes.Document

fun analyzeDocument(document: Document): Pair<Int, Int> =
    document.select("rect[data-count]")
        .sortedBy { it.attr("data-date") }
        .map { it.attr("data-count").toInt() }
        .let { it.last() to it.takeLastWhile { count -> count > 0 }.count() }
