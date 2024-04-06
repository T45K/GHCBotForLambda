package io.github.t45k.ghcbonk.util

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

data class JstDate private constructor(val value: ZonedDateTime) {
    companion object {
        private val jst = ZoneId.of("Asia/Tokyo")
        fun from(value: ZonedDateTime): JstDate = JstDate(value.withZoneSameInstant(jst).truncatedTo(ChronoUnit.DAYS))
    }
}
