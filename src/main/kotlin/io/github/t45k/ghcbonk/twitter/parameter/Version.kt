package io.github.t45k.ghcbonk.twitter.parameter

class Version : Parameter("1.0") {
    companion object {
        private const val OAUTH_VERSION = "oauth_version"
    }

    override fun key(): String = OAUTH_VERSION
}
