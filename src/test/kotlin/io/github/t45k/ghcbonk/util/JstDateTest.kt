package io.github.t45k.ghcbonk.util

import kotlin.test.Test
import kotlin.test.assertEquals
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

class JstDateTest {
    @Test
    fun from() {
        val dateTime = ZonedDateTime.parse("2024-01-02T15:06:07Z")
        assertEquals(ZonedDateTime.parse("2024-01-03T00:00:00+09:00").toInstant(), JstDate.from(dateTime).toInstant())
    }

    @Test
    fun now() {
        val dateTime = LocalDate.now().atStartOfDay().atZone(ZoneId.of("Asia/Tokyo"))
        assertEquals(JstDate.now().toInstant(), dateTime.toInstant())
    }
}
