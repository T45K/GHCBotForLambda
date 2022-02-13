package io.github.t45k.ghcbonk

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import io.github.t45k.ghcbonk.github.ContributionCount
import io.github.t45k.ghcbonk.github.GitHubUser
import io.github.t45k.ghcbonk.github.analyzeDocument
import io.github.t45k.ghcbonk.twitter.TwitterClient
import io.github.t45k.ghcbonk.twitter.tweetModel.SimpleTweetModel
import io.github.t45k.ghcbonk.twitter.tweetModel.WordleLikeTweetModel
import java.time.LocalDate
import java.util.ResourceBundle

class MyLambda : RequestHandler<Unit, String?> {
    override fun handleRequest(input: Unit, context: Context?): String {
        val property: ResourceBundle = ResourceBundle.getBundle("info")
        val githubUserName = property.getString("userName")
        val contributionCountsLast40days: List<ContributionCount> = GitHubUser(githubUserName)
            .fetchGitHubUserPage()
            .let(::analyzeDocument)

        val today = LocalDate.now()
        val simpleTweet = SimpleTweetModel(githubUserName)
        val wordleLikeTweet = WordleLikeTweetModel()

        return TwitterClient(
            property.getString("apiKey"),
            property.getString("apiSecret"),
            property.getString("token"),
            property.getString("tokenSecret")
        )
            .run {
                tweet(simpleTweet.getContent(today, contributionCountsLast40days)).toString() + "\n" +
                    tweet(wordleLikeTweet.getContent(today, contributionCountsLast40days)).toString()
            }
    }
}
