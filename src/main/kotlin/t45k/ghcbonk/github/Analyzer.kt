package t45k.ghcbonk.github

fun analyzeRawData(rawData: List<String>, userName: String): ContributionData {
    var counter = 0

    for (oneDayActivity: String in rawData.reversed()) {
        val elements: List<String> = oneDayActivity.split(Regex("\\s+"))
        val numOfContributionOfTheDay: String = getStringInDoubleQuote(elements[8])

        if (numOfContributionOfTheDay == "0") {
            counter = 0
            break
        }

        counter++
    }

    val lastActivity: List<String> = rawData[rawData.size - 1].split(Regex("\\s+"))
    val namOfContribution: String = getStringInDoubleQuote(lastActivity[8])
    val date: String = getStringInDoubleQuote(lastActivity[9])

    return ContributionData(userName, date, namOfContribution, counter)
}

fun getStringInDoubleQuote(string: String): String {
    val begin: Int = string.indexOf("\"") + 1
    val end: Int = string.lastIndexOf("\"")
    return string.substring(begin, end)
}
