package com.coreydowning.adventofcode2021

import io.kotest.assertions.asClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CrabSubmarineAlignerTest : FunSpec({
    context("sample data") {
        val crabSubmarines = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
        val aligner = CrabSubmarineAligner(crabSubmarines)

        withData(
            nameFn = { (position, fuel) -> "fuelToMoveToPosition($position) should be $fuel" },
            2 to 206L,
            5 to 168L,
        ) { (position, fuel) ->
            aligner.crabSubmarines.asClue {
                aligner.fuelToMoveToPosition(position) shouldBe fuel
            }
        }

        test("cheapestPosition") {
            aligner.crabSubmarines.asClue {
                aligner.cheapestPosition() shouldBe (5 to 168L)
            }
        }
    }

})
