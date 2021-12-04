package com.coreydowning.adventofcode2021

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day3BinaryDiagnosticKtTest : FunSpec({
    xcontext("part 1") {
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

    context("part 2") {
        test("oxygen") {
            Part1.oxygen(listOf("0"), 0, 1) shouldBe 0
            Part1.oxygen(listOf("1"), 0, 1) shouldBe 1
            Part1.oxygen(listOf("01", "10"), 0, 2) shouldBe 2
            Part1.oxygen(listOf("11", "10"), 0, 2) shouldBe 3
            Part1.oxygen(listOf("000", "001", "010", "011", "100", "101", "110", "111"), 0, 3) shouldBe 7
            Part1.oxygen(listOf("1000", "1001", "1011", "1110"), 0, 4) shouldBe 9
        }

        test("co2") {
            Part1.co2(listOf("0"), 0, 1) shouldBe 0
            Part1.co2(listOf("1"), 0, 1) shouldBe 1
            Part1.co2(listOf("01", "10"), 0, 2) shouldBe 1
//            Part1.co2(listOf("11", "10"), 0, 2) shouldBe 2
            Part1.co2(listOf("000", "001", "010", "011", "100", "101", "110", "111"), 0, 3) shouldBe 0
            Part1.co2(listOf("1000", "0101", "0011", "1110"), 0, 4) shouldBe 3
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
                oxygenGeneratorRating = 23,
                co2ScrubberRating = 10,
            )
        }
    }
})
