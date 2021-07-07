package io.github.t45k.ghcbonk.util

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

interface StringMixin {
    fun String.percentEncode() = URLEncoder.encode(this, StandardCharsets.UTF_8).replace("+", "%20")
}
