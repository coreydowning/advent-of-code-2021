package com.coreydowning.adventofcode2021

import com.coreydowning.adventofcode2021.Lanternfish.Companion.JUVENILE_REPRODUCTION_DAYS
import com.coreydowning.adventofcode2021.Lanternfish.Companion.REPRODUCTION_DAYS
import io.kotest.assertions.asClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe

class Day6LanternfishKtTest : FunSpec({
    context("Lanternfish") {
        test("advanceDay") {
            Lanternfish(daysUntilReproduction = 6).asClue { it.advanceDay() shouldBe Lanternfish(daysUntilReproduction = 5) }
            Lanternfish(daysUntilReproduction = 0).asClue { it.advanceDay() shouldBe Lanternfish(daysUntilReproduction = REPRODUCTION_DAYS) }
        }
    }

    context("Simulation") {
        test("advanceDay") {
            Simulation(listOf(Lanternfish(daysUntilReproduction = 0))).asClue {
                it.advanceDay() shouldBe Simulation(listOf(
                    Lanternfish(daysUntilReproduction = REPRODUCTION_DAYS),
                    Lanternfish(daysUntilReproduction = JUVENILE_REPRODUCTION_DAYS),
                ))
            }
        }

        test("sample input") {
            val input = Simulation(listOf(
                3.asFish(),
                4.asFish(),
                3.asFish(),
                1.asFish(),
                2.asFish(),
            ))

            val eighteenDays = (0 until 18).fold(input) { simulation, _ -> simulation.advanceDay() }
            eighteenDays.fish shouldHaveSize 26
            val eighty = (0 until 80).fold(input) { simulation, _ -> simulation.advanceDay() }
            eighty.fish shouldHaveSize 5934
        }
    }

    context("OptimizedSimulation") {
        test("advanceDay") {
            OptimizedSimulation(listOf(
                Lanternfish(daysUntilReproduction = 0), Lanternfish(daysUntilReproduction = 2),
            )).asClue {
                it.advanceDay()

                it.fishReproducingPerDay shouldContain (1 to 1L)
                it.fishReproducingPerDay shouldContain (REPRODUCTION_DAYS to 1L)
                it.fishReproducingPerDay shouldContain (JUVENILE_REPRODUCTION_DAYS to 1L)
                it.totalFish shouldBe 3

                it.advanceDay()
                it.advanceDay()

                it.fishReproducingPerDay shouldContain (4 to 1L)
                it.fishReproducingPerDay shouldContain (REPRODUCTION_DAYS to 2L)
                it.fishReproducingPerDay shouldContain (JUVENILE_REPRODUCTION_DAYS to 1L)
                it.totalFish shouldBe 4
            }
        }

        test("sample input") {
            val simulation = OptimizedSimulation(listOf(
                3.asFish(),
                4.asFish(),
                3.asFish(),
                1.asFish(),
                2.asFish(),
            ))

            simulation.asClue {
                (0 until 18).forEach { _ -> simulation.advanceDay() }
                it.totalFish shouldBe 26

                (18 until 80).forEach { _ -> simulation.advanceDay() }
                it.totalFish shouldBe 5934

                (80 until 256).forEach { _ -> simulation.advanceDay() }
                it.totalFish shouldBe 26984457539L
            }
        }
    }

})
