package io.github.t45k.ghcbonk.twitter.tweetModel

import io.github.t45k.ghcbonk.github.ContributionCount
import io.github.t45k.ghcbonk.util.JstDate

interface TweetModel {
    fun getContent(yesterday: JstDate, contributionCounts: List<ContributionCount>): String
}
