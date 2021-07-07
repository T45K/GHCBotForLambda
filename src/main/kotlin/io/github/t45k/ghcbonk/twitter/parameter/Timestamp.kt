package io.github.t45k.ghcbonk.twitter.parameter

class Timestamp : Parameter((System.currentTimeMillis() / 1000).toString()) {
    companion object {
        private const val OAUTH_TIMESTAMP = "oauth_timestamp"
    }

    override fun key(): String = OAUTH_TIMESTAMP
}
