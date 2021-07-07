package io.github.t45k.ghcbonk.twitter.parameter

import io.github.t45k.ghcbonk.util.StringMixin

abstract class Parameter(private val value: String) : StringMixin {
    fun toSignatureBaseString() = "${key()}=${value.percentEncode()}"
    fun toHeaderString() = "${key()}=\"${value.percentEncode()}\""

    abstract fun key(): String
}
