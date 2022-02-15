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
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.url
import kotlinx.coroutines.runBlocking

class TwitterClient(
    private val apiKey: String,
    private val apiSecret: String,
    private val token: String,
    private val tokenSecret: String
) : StringMixin {

    fun tweet(content: String) {
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

        runBlocking {
            HttpClient().use {
                it.post {
                    header("Authorization", header)
                    url(Constants.API_URL_BASE)
                    parameter("status", content)
                }
            }
        }
    }
}
