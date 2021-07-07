package io.github.t45k.ghcbonk.twitter.parameter

class ConsumerKey(value: String) : Parameter(value) {
    companion object {
        private const val OAUTH_CONSUMER_KEY = "oauth_consumer_key"
    }

    override fun key(): String = OAUTH_CONSUMER_KEY
}
