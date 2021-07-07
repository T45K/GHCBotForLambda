package io.github.t45k.ghcbonk.twitter.parameter

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

abstract class Parameter(private val value: String) {
    fun toSignatureBaseString() = "${key()}=${value.percentEncode()}"
    fun toHeaderString() = "${key()}=\"${value.percentEncode()}\""

    abstract fun key(): String

    private fun String.percentEncode() = URLEncoder.encode(this, StandardCharsets.UTF_8).replace("+", "%20")
}
