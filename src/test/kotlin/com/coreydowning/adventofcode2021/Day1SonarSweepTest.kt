package com.coreydowning.adventofcode2021

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day1SonarSweepTest : FunSpec({
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
})
