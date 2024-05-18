package io.github.t45k.ghcbonk.util

import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Suppress("DataClassPrivateConstructor")
data class JstDate private constructor(private val value: ZonedDateTime) : Comparable<JstDate> {
    val dayOfWeek: DayOfWeek = value.dayOfWeek

    companion object {
        private val jst = ZoneId.of("Asia/Tokyo")
        fun from(value: ZonedDateTime): JstDate = JstDate(value.withZoneSameInstant(jst).truncatedTo(ChronoUnit.DAYS))
        fun of(year: Int, month: Int, dayOfMonth: Int): JstDate =
            JstDate(ZonedDateTime.of(year, month, dayOfMonth, 0, 0, 0, 0, jst))

        fun now(): JstDate = from(ZonedDateTime.now())
    }

    operator fun minus(days: Long): JstDate = JstDate(value.minusDays(days))
    operator fun plus(days: Long): JstDate = JstDate(value.plusDays(days))

    fun toInstant(): Instant = value.toInstant()

    override fun compareTo(other: JstDate): Int = this.value.compareTo(other.value)

    fun format(formatter: DateTimeFormatter): String = value.format(formatter)
}
