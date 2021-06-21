package t45k.ghcbonk.twitter

import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

class TwitterClient(
    apiKey: String,
    apiSecret: String,
    token: String,
    tokenSecret: String
) {
    private val twitter: Twitter = TwitterFactory().instance
        .also { it.setOAuthConsumer(apiKey, apiSecret) }
        .also { it.oAuthAccessToken = AccessToken(token, tokenSecret) }

    fun tweet(content: String) {
        this.twitter.updateStatus(content)
    }
}