package io.github.t45k.ghcbonk.twitter

import io.github.t45k.ghcbonk.twitter.parameter.ConsumerKey
import io.github.t45k.ghcbonk.twitter.parameter.Nonce
import io.github.t45k.ghcbonk.twitter.parameter.Signature
import io.github.t45k.ghcbonk.twitter.parameter.SignatureMethod
import io.github.t45k.ghcbonk.twitter.parameter.Status
import io.github.t45k.ghcbonk.twitter.parameter.Timestamp
import io.github.t45k.ghcbonk.twitter.parameter.Token
import io.github.t45k.ghcbonk.twitter.parameter.Version
import io.github.t45k.ghcbonk.util.Constants
import io.github.t45k.ghcbonk.util.StringMixin
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.EMPTY_REQUEST

class TwitterClient(
    private val apiKey: String,
    private val apiSecret: String,
    private val token: String,
    private val tokenSecret: String
) : StringMixin {

    fun tweet(content: String): Response {
        val consumerKey = ConsumerKey(apiKey)
        val nonce = Nonce()
        val signatureMethod = SignatureMethod()
        val status = Status(content)
        val timestamp = Timestamp()
        val token = Token(token)
        val version = Version()
        val signature = Authorization(apiSecret, tokenSecret)
            .createSignature(
                status,
                consumerKey,
                nonce,
                signatureMethod,
                timestamp,
                token,
                version
            ).let(::Signature)

        val httpUrl = Constants.API_URL_BASE.toHttpUrl().newBuilder()
            .addQueryParameter("status", content)
            .build()

        val header = "OAuth " +
            listOf(
                consumerKey,
                nonce,
                signature,
                signatureMethod,
                timestamp,
                token,
                version
            ).joinToString(", ") { it.toHeaderString() }

        return Request.Builder()
            .url(httpUrl)
            .header("Authorization", header)
            .post(EMPTY_REQUEST)
            .build()
            .let(OkHttpClient()::newCall)
            .execute()
    }
}
