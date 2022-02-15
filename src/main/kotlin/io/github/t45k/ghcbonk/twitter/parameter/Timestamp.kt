package io.github.t45k.ghcbonk.twitter.parameter

class Timestamp : Parameter((System.currentTimeMillis() / 1000).toString()) {
    override val key: String = "oauth_timestamp"
}
