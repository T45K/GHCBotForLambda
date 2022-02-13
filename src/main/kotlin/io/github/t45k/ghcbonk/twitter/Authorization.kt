package io.github.t45k.ghcbonk.twitter

import io.github.t45k.ghcbonk.twitter.parameter.ConsumerKey
import io.github.t45k.ghcbonk.twitter.parameter.IncludeEntities
import io.github.t45k.ghcbonk.twitter.parameter.Nonce
import io.github.t45k.ghcbonk.twitter.parameter.Parameter
import io.github.t45k.ghcbonk.twitter.parameter.SignatureMethod
import io.github.t45k.ghcbonk.twitter.parameter.Status
import io.github.t45k.ghcbonk.twitter.parameter.Timestamp
import io.github.t45k.ghcbonk.twitter.parameter.Token
import io.github.t45k.ghcbonk.twitter.parameter.Version
import io.github.t45k.ghcbonk.util.Constants
import io.github.t45k.ghcbonk.util.StringMixin
import java.time.LocalDate
import java.util.Base64
import java.util.StringJoiner
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class Authorization(
    private val consumerSecret: String,
    private val tokenSecret: String
) : StringMixin {
    companion object {
        private const val HTTP_METHOD = "POST"
        private const val DIGEST_ALGORITHM = "HmacSHA1"
    }

    fun createSignature(
        status: Status,
        includeEntities: IncludeEntities,
        consumerKey: ConsumerKey,
        nonce: Nonce,
        signatureMethod: SignatureMethod,
        timestamp: Timestamp,
        token: Token,
        version: Version
    ): String {
        LocalDate.now().dayOfWeek
        val signatureBase: String = StringJoiner("&")
            .add(HTTP_METHOD)
            .add(Constants.API_URL_BASE.percentEncode())
            .add(
                joinParams(
                    status,
                    includeEntities,
                    consumerKey,
                    nonce,
                    signatureMethod,
                    timestamp,
                    token,
                    version
                ).percentEncode()
            )
            .toString()

        val signingKey: String = listOf(
            consumerSecret,
            tokenSecret
        ).joinToString("&") { it.percentEncode() }

        return Mac.getInstance(DIGEST_ALGORITHM)
            .apply { init(SecretKeySpec(signingKey.toByteArray(), DIGEST_ALGORITHM)) }
            .doFinal(signatureBase.toByteArray())
            .let(Base64.getEncoder()::encodeToString)
    }

    private fun joinParams(vararg params: Parameter) =
        params
            .sortedBy { it.key() }
            .joinToString("&") { it.toSignatureBaseString() }
}
