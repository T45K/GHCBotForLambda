package io.github.t45k.ghcbonk.twitter.tweetModel

import io.github.t45k.ghcbonk.github.ContributionCount
import java.time.LocalDate

interface TweetModel {
    fun getContent(today: LocalDate, contributionCounts: List<ContributionCount>): String
}
