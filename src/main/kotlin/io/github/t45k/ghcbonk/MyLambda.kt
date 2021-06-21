package io.github.t45k.ghcbonk

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import io.github.t45k.ghcbonk.github.GitHubUser
import io.github.t45k.ghcbonk.github.analyzeDocument
import io.github.t45k.ghcbonk.twitter.TweetModel
import io.github.t45k.ghcbonk.twitter.TwitterClient
import java.util.ResourceBundle

class MyLambda : RequestHandler<Unit, String> {
    override fun handleRequest(input: Unit, context: Context?): String =
        try {
            val property: ResourceBundle = ResourceBundle.getBundle("info")
            val githubUserName = property.getString("userName")
            val (lastContributionCount, contributionStreak) = GitHubUser(githubUserName)
                .fetchGitHubUserPage()
                .let(::analyzeDocument)

            val tweet = TweetModel(githubUserName, lastContributionCount, contributionStreak)
            TwitterClient(
                property.getString("apiKey"),
                property.getString("apiSecret"),
                property.getString("token"),
                property.getString("tokenSecret")
            ).tweet(tweet.getContent())

            "success"
        } catch (e: Exception) {
            e.toString()
        }
}
