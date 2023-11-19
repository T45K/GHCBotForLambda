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
import java.lang.NullPointerException
import java.time.LocalDate

class MyLambda : RequestHandler<Unit, Unit> {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    override fun handleRequest(input: Unit, context: Context) {
        val githubUserName = System.getenv("username") ?: run {
            logger.error("Failed to get username from environment variables. Have you set it?")
            return
        }
        val contributionCounts: List<ContributionCount> = GitHubUser(githubUserName)
            .fetchGitHubUserPage()
            .let(::analyzeDocument)

        val today = LocalDate.now()

        val twitterClient = try {
            TwitterClient(
                System.getenv("apiKey"),
                System.getenv("apiSecret"),
                System.getenv("token"),
                System.getenv("tokenSecret")
            )
        } catch (e: NullPointerException) {
            logger.error("Failed to get apiKey, apiSecret, token or tokenSecret. Have you set them?")
            return
        }

        val tweetModels = listOf(
            SimpleTweetModel(githubUserName),
            WordleLikeTweetModel()
        )
        for (tweetModel in tweetModels) {
            try {
                val response = twitterClient.tweet(tweetModel.getContent(today, contributionCounts))
                if (response == null) {
                    logger.warn("Response for ${tweetModel.javaClass.simpleName} is null")
                } else if (!response.errors.isNullOrEmpty()) {
                    logger.warn("Response for ${tweetModel.javaClass.simpleName} has the following errors\n${response.errors}")
                }
            } catch (e: Exception) {
                logger.warn("Failed to send ${tweetModel.javaClass.simpleName} due to exception", e)
            }
        }
    }
}
