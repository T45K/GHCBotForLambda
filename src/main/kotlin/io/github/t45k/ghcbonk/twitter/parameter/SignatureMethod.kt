package io.github.t45k.ghcbonk.twitter.parameter

class SignatureMethod : Parameter("HMAC-SHA1") {
    override val key: String = "oauth_signature_method"
}
