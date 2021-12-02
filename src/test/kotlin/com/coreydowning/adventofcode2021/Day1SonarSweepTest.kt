package com.coreydowning.adventofcode2021

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day1SonarSweepTest : FunSpec({
    context("part 1, window size 1") {
        test("with no inputs") {
            Day1SonarSweep.sweep(emptyList()) shouldBe 0
        }

        test("with one input") {
            Day1SonarSweep.sweep((listOf(200))) shouldBe 0
        }

        test("with two inputs") {
            Day1SonarSweep.sweep(listOf(200, 201)) shouldBe 1
            Day1SonarSweep.sweep(listOf(200, 200)) shouldBe 0
            Day1SonarSweep.sweep(listOf(200, 199)) shouldBe 0
        }

        test("with sample inputs returns 7") {
            Day1SonarSweep.sweep(
                listOf(
                    199,
                    200,
                    208,
                    210,
                    200,
                    207,
                    240,
                    269,
                    260,
                    263,
                )
            ) shouldBe 7
        }
    }

    context("part 2, window size 3") {
        test("with too few inputs") {
            Day1SonarSweep.sweep(emptyList(), 3) shouldBe 0
            Day1SonarSweep.sweep(listOf(200), 3) shouldBe 0
            Day1SonarSweep.sweep(listOf(200, 200), 3) shouldBe 0
            Day1SonarSweep.sweep(listOf(200, 200, 200), 3) shouldBe 0
        }

        test("with four inputs") {
            Day1SonarSweep.sweep(listOf(199, 200, 208, 200), 3) shouldBe 1
            Day1SonarSweep.sweep(listOf(199, 200, 208, 198), 3) shouldBe 0
            Day1SonarSweep.sweep(listOf(199, 200, 208, 199), 3) shouldBe 0
        }

        test("with sample inputs") {
            Day1SonarSweep.sweep(
                listOf(
                    199,
                    200,
                    208,
                    210,
                    200,
                    207,
                    240,
                    269,
                    260,
                    263,
                ), 3
            ) shouldBe 5
        }

    }
})
