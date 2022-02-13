package io.github.t45k.ghcbonk.github

import java.time.LocalDate

data class ContributionCount(val date: LocalDate, val count: Int)

infix fun LocalDate.to(count: Int): ContributionCount = ContributionCount(this, count)
