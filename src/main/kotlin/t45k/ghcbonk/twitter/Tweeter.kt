package t45k.ghcbonk.twitter

import t45k.ghcbonk.github.ContributionData
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import java.util.ResourceBundle

class Tweeter(resource: ResourceBundle) {
    private val twitter: Twitter = TwitterFactory().instance

    init {
        val apiKey: String = resource.getString("apiKey")
        val apiSecret: String = resource.getString("apiSecret")
        val token: String = resource.getString("token")
        val tokenSecret: String = resource.getString("tokenSecret")

        twitter.setOAuthConsumer(apiKey, apiSecret)
        val accessToken = AccessToken(token, tokenSecret)
        twitter.oAuthAccessToken = accessToken
    }

    fun tweet(data: ContributionData) {
        this.twitter.updateStatus(getContents(data))
    }

    private fun getContents(data: ContributionData): String =
            """
            |${data.date}のコントリビューション数: ${data.numOfDayContribution}
            |連続コントリビューション日数: ${data.numOfContinuousContribution}
            |https://github.com/${data.userName}
            """.trimMargin()
}