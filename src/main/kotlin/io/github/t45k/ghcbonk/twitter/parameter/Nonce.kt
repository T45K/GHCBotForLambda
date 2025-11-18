package io.github.t45k.ghcbonk.twitter.parameter

import kotlin.random.Random

class Nonce : Parameter(random.nextLong().toString()) {
    companion object {
        private val random = Random.Default
    }

    override val key: String = "oauth_nonce"
}
