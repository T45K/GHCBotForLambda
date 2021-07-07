package io.github.t45k.ghcbonk.twitter.parameter

class SignatureMethod : Parameter(HMAC_SHA1) {
    companion object {
        private const val OAUTH_SIGNATURE_METHOD = "oauth_signature_method"
        private const val HMAC_SHA1 = "HMAC-SHA1"
    }

    override fun key(): String = OAUTH_SIGNATURE_METHOD
}
