package io.github.t45k.ghcbonk

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import io.github.t45k.ghcbonk.github.ContributionCount
import io.github.t45k.ghcbonk.github.GitHubUser
import io.github.t45k.ghcbonk.github.analyzeDocument
import io.github.t45k.ghcbonk.twitter.TwitterClient
import io.github.t45k.ghcbonk.twitter.tweetModel.SimpleTweetModel
import io.github.t45k.ghcbonk.twitter.tweetModel.WordleLikeTweetModel
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.util.ResourceBundle

class MyLambda : RequestHandler<Unit, Unit> {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun handleRequest(input: Unit, context: Context?) {
        val property: ResourceBundle = ResourceBundle.getBundle("info")
        val githubUserName = property.getString("userName")
        val contributionCounts: List<ContributionCount> = GitHubUser(githubUserName)
            .fetchGitHubUserPage()
            .let(::analyzeDocument)

        val today = LocalDate.now()
        val simpleTweet = SimpleTweetModel(githubUserName)
        val wordleLikeTweet = WordleLikeTweetModel()

        val twitterClient = TwitterClient(
            property.getString("apiKey"),
            property.getString("apiSecret"),
            property.getString("token"),
            property.getString("tokenSecret")
        )
        val response1 = twitterClient.tweet(simpleTweet.getContent(today, contributionCounts))
        if (response1.code != 200) {
            logger.warn("Failure for Simple Tweet")
            logger.warn(response1.body?.string())
        }

        val response2 = twitterClient.tweet(wordleLikeTweet.getContent(today, contributionCounts))
        if (response2.code != 200) {
            logger.warn("Failure for Wordle-like Tweet")
            logger.warn(response2.body?.string())
        }
    }
}
