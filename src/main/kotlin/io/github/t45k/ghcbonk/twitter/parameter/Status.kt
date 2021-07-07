package io.github.t45k.ghcbonk.twitter.parameter

class Status(value: String) : Parameter(value) {
    companion object {
        private const val STATUS = "status"
    }

    override fun key(): String = STATUS
}
