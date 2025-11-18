package io.github.t45k.ghcbonk.twitter.parameter

import io.github.t45k.ghcbonk.util.StringMixin

abstract class Parameter(private val value: String) : StringMixin {
    abstract val key: String

    fun toSignatureBaseString() = "${this.key}=${value.percentEncode()}"
    fun toHeaderString() = "${this.key}=\"${value.percentEncode()}\""
}
