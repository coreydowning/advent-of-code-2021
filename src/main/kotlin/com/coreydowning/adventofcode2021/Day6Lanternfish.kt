package com.coreydowning.adventofcode2021

import com.coreydowning.adventofcode2021.Lanternfish.Companion.JUVENILE_REPRODUCTION_DAYS
import com.coreydowning.adventofcode2021.Lanternfish.Companion.REPRODUCTION_DAYS

fun Int.asFish(): Lanternfish = Lanternfish(this)

@JvmInline
value class Lanternfish(
    val daysUntilReproduction: Int,
) {
    fun advanceDay(): Lanternfish {
        return if (this.daysUntilReproduction == 0) {
            Lanternfish(daysUntilReproduction = REPRODUCTION_DAYS)
        } else {
            Lanternfish(daysUntilReproduction = (daysUntilReproduction - 1))
        }
    }

    companion object {
        const val REPRODUCTION_DAYS: Int = 6
        const val JUVENILE_REPRODUCTION_DAYS: Int = (REPRODUCTION_DAYS + 2)

        fun makeBaby(): Lanternfish = Lanternfish(daysUntilReproduction = JUVENILE_REPRODUCTION_DAYS)
    }
}

data class Simulation(
    val fish: List<Lanternfish>,
) {
    fun advanceDay(): Simulation {
        val juveniles = fish.count { it.daysUntilReproduction == 0 }
        return Simulation(
            fish.map { it.advanceDay() }.plus((0..juveniles).map {
                if (it > 0) {
                    Lanternfish.makeBaby()
                } else {
                    null
                }
            }).filterNotNull()
        )
    }
}

data class OptimizedSimulation(
    val initialFish: List<Lanternfish>,
    var fishReproducingPerDay: Map<Int, Long> = initialFish
        .sortedBy { it.daysUntilReproduction }
        .groupBy { it.daysUntilReproduction }
        .map { (days, fish) -> days to fish.size.toLong() }
        .toMap(),
) {
    val totalFish: Long get() = fishReproducingPerDay.values.sumOf { it ?: 0L }

    fun advanceDay() {
        val juveniles = fishReproducingPerDay[0] ?: 0
        fishReproducingPerDay =
            (1..JUVENILE_REPRODUCTION_DAYS).associate { days -> (days - 1) to fishReproducingPerDay[days] }
                .plus(REPRODUCTION_DAYS to (juveniles + (fishReproducingPerDay[REPRODUCTION_DAYS + 1] ?: 0)))
                .plus(JUVENILE_REPRODUCTION_DAYS to juveniles)
                .map { (days, count) -> days to (count ?: 0L) }
                .toMap()
    }
}