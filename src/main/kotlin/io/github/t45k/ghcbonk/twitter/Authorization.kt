package io.github.t45k.ghcbonk.twitter

import io.github.t45k.ghcbonk.twitter.parameter.ConsumerKey
import io.github.t45k.ghcbonk.twitter.parameter.Nonce
import io.github.t45k.ghcbonk.twitter.parameter.Parameter
import io.github.t45k.ghcbonk.twitter.parameter.SignatureMethod
import io.github.t45k.ghcbonk.twitter.parameter.Timestamp
import io.github.t45k.ghcbonk.twitter.parameter.Token
import io.github.t45k.ghcbonk.twitter.parameter.Version
import io.github.t45k.ghcbonk.util.Constants
import io.github.t45k.ghcbonk.util.StringMixin
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.io.encoding.Base64

class Authorization(
    private val consumerSecret: String,
    private val tokenSecret: String
) : StringMixin {
    companion object {
        private const val HTTP_METHOD = "POST"
        private const val DIGEST_ALGORITHM = "HmacSHA1"
    }

    fun createSignature(
        consumerKey: ConsumerKey,
        nonce: Nonce,
        signatureMethod: SignatureMethod,
        timestamp: Timestamp,
        token: Token,
        version: Version
    ): String {
        val signatureBase: String = listOf(
            HTTP_METHOD,
            Constants.API_URL_BASE,
            joinParams(
                consumerKey,
                nonce,
                signatureMethod,
                timestamp,
                token,
                version
            ),
        ).joinToString("&") { it.percentEncode() }

        val signingKey: String = listOf(
            consumerSecret,
            tokenSecret
        ).joinToString("&") { it.percentEncode() }

        return Mac.getInstance(DIGEST_ALGORITHM)
            .apply { init(SecretKeySpec(signingKey.toByteArray(), DIGEST_ALGORITHM)) }
            .doFinal(signatureBase.toByteArray())
            .let(Base64::encode)
    }

    private fun joinParams(vararg params: Parameter) =
        params
            .sortedBy { it.key }
            .joinToString("&") { it.toSignatureBaseString() }
}
