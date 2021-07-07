package io.github.t45k.ghcbonk.twitter.parameter

import kotlin.random.Random

class Nonce : Parameter(random.nextLong().toString()) {
    companion object {
        private const val OAUTH_NONCE = "oauth_nonce"
        private val random = Random.Default
    }

    override fun key(): String = OAUTH_NONCE
}
