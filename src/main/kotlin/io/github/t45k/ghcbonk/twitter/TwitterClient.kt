package io.github.t45k.ghcbonk.twitter

import io.github.t45k.ghcbonk.twitter.parameter.ConsumerKey
import io.github.t45k.ghcbonk.twitter.parameter.Nonce
import io.github.t45k.ghcbonk.twitter.parameter.Signature
import io.github.t45k.ghcbonk.twitter.parameter.SignatureMethod
import io.github.t45k.ghcbonk.twitter.parameter.Timestamp
import io.github.t45k.ghcbonk.twitter.parameter.Token
import io.github.t45k.ghcbonk.twitter.parameter.Version
import io.github.t45k.ghcbonk.util.Constants
import io.github.t45k.ghcbonk.util.StringMixin
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.intellij.lang.annotations.Language

class TwitterClient(
    private val apiKey: String,
    private val apiSecret: String,
    private val token: String,
    private val tokenSecret: String
) : StringMixin {
    private val httpClient: OkHttpClient = OkHttpClient()

    private val json = Json { ignoreUnknownKeys = true }

    fun tweet(content: String): Response {
        val request = Request.Builder()
            .url(Constants.API_URL_BASE)
            .post(makeRequestBody(content))
            .header("Authorization", makeAuthorizationHeaderValue())
            .build()

        return httpClient.newCall(request).execute()
    }

    private fun makeAuthorizationHeaderValue(): String {
        val consumerKey = ConsumerKey(apiKey)
        val nonce = Nonce()
        val signatureMethod = SignatureMethod()
        val timestamp = Timestamp()
        val token = Token(token)
        val version = Version()
        val signature = Authorization(apiSecret, tokenSecret)
            .createSignature(
                consumerKey,
                nonce,
                signatureMethod,
                timestamp,
                token,
                version
            ).let(::Signature)

        return "OAuth " +
            listOf(
                consumerKey,
                nonce,
                signature,
                signatureMethod,
                timestamp,
                token,
                version
            ).joinToString(", ") { it.toHeaderString() }
    }

    private fun makeRequestBody(content: String): RequestBody {
        val textJson = json.encodeToString(JsonPrimitive.serializer(), JsonPrimitive(content))
        val requestBodyString = @Language("JSON") """
            { "text": $textJson }
        """.trimIndent()

        val mediaType = "application/json; charset=utf-8".toMediaType()
        return requestBodyString.toRequestBody(mediaType)
    }
}
