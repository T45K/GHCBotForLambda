package io.github.t45k.ghcbonk.twitter.parameter

class IncludeEntities : Parameter(true.toString()) {
    companion object {
        private const val INCLUDE_ENTITIES = "include_entities"
    }

    override fun key(): String = INCLUDE_ENTITIES
}
