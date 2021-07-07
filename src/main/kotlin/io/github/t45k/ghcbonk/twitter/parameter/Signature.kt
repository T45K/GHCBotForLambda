package io.github.t45k.ghcbonk.twitter.parameter

class Signature(value: String) : Parameter(value) {
    companion object {
        private const val OAUTH_SIGNATURE = "oauth_signature"
    }

    override fun key(): String = OAUTH_SIGNATURE
}