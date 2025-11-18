package io.github.t45k.ghcbonk.twitter.parameter

class Signature(value: String) : Parameter(value) {
    override val key: String = "oauth_signature"
}
