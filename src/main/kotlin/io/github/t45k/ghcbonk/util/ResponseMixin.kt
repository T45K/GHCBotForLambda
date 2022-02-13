package io.github.t45k.ghcbonk.util

import okhttp3.Response

interface ResponseMixin {
    fun Response.isFailure() = !this.isSuccessful
}
