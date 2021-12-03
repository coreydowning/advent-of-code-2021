package com.coreydowning.adventofcode2021

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day3BinaryDiagnosticKtTest : FunSpec({
    context("part 1") {
        test("one small piece") {
            gammaOf(false) shouldBe false
            gammaOf(true) shouldBe true
            // This problem definition is pretty annoying because an equal number of 1s and 0s is undefined behavior.
            gammaOf(false, false, false) shouldBe false
            gammaOf(false, false, true) shouldBe false
            gammaOf(false, true, true) shouldBe true
            gammaOf(true, true, true) shouldBe true
        }

        test("the whole shebang") {
            val readings = """
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010
            """.trimIndent().lines()

            Part1.calculate(readings) shouldBe BinaryDiagnostics(
                gammaRate = 22,
                epsilonRate = 9,
                oxygenGeneratorRating = 0,
                co2ScrubberRating = 0,
            )
        }
    }

    xcontext("part 2") {
        test("the whole shebang") {
            val readings = """
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010
            """.trimIndent().lines()

            Part1.calculate(readings) shouldBe BinaryDiagnostics(
                gammaRate = 22,
                epsilonRate = 9,
                oxygenGeneratorRating = 23,
                co2ScrubberRating = 0,
            )
        }
    }
})
