package io.github.t45k.ghcbonk.twitter

import com.twitter.clientlib.TwitterCredentialsOAuth1
import com.twitter.clientlib.api.TwitterApi
import com.twitter.clientlib.model.CreateTweetRequest
import com.twitter.clientlib.model.TweetCreateResponse
import io.github.t45k.ghcbonk.util.StringMixin

class TwitterClient(
    private val apiKey: String,
    private val apiSecret: String,
    private val token: String,
    private val tokenSecret: String
) : StringMixin {
    private val apiInstance = TwitterApi().apply {
        val credential = TwitterCredentialsOAuth1(apiKey, apiSecret, token, tokenSecret)
        setTwitterCredentials(credential)
    }

    fun tweet(content: String): TweetCreateResponse? {
        val request = CreateTweetRequest().apply {
            text = content
        }
        return apiInstance.tweets().createTweet(request)
    }
}
