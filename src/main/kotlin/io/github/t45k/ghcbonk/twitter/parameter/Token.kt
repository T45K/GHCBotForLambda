package io.github.t45k.ghcbonk.twitter.parameter

class Token(value: String) : Parameter(value) {
    companion object {
        private const val OAUTH_TOKEN = "oauth_token"
    }

    override fun key(): String = OAUTH_TOKEN
}
