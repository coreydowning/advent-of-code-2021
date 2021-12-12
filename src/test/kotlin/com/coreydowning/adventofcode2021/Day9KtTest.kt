package com.coreydowning.adventofcode2021


import io.kotest.assertions.asClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class Day9KtTest : FunSpec({
    context("sample input") {
        val input = listOf(
            2, 1, 9, 9, 9, 4, 3, 2, 1, 0,
            3, 9, 8, 7, 8, 9, 4, 9, 2, 1,
            9, 8, 5, 6, 7, 8, 9, 8, 9, 2,
            8, 7, 6, 7, 8, 9, 6, 7, 8, 9,
            9, 8, 9, 9, 9, 6, 5, 6, 7, 8,
        )
        val heightmap = Heightmap(input, 10)

        withData(
            0 to listOf(1, 10),
            1 to listOf(0, 2, 11),
            9 to listOf(8, 19),
            10 to listOf(0, 11, 20),
            22 to listOf(21, 23, 12, 32),
            40 to listOf(30, 41),
            45 to listOf(35, 44, 46),
            49 to listOf(48, 39),
        ) { (position, adjacentPositions) ->
            heightmap.adjacentTo(position) shouldContainExactlyInAnyOrder adjacentPositions
        }

        test("three adjacent points - row 0 col 1") {
            heightmap.adjacentTo(1) shouldContainExactlyInAnyOrder listOf(0, 2, 11)
        }

        test("low points") {
            heightmap.asClue { heightmap.lowPoints shouldContainExactlyInAnyOrder listOf(1, 9, 22, 46) }
        }

        test("sum of risk levels") {
            heightmap.asClue { heightmap.riskLevel shouldBe 15 }
        }

        test("basins") {
            heightmap.basins.asClue {
                it shouldContainExactly setOf(
                    setOf(0, 1, 10),
                    setOf(9, 19, 29, 8, 18, 7, 6, 16, 5),
                    setOf(12, 13, 14, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34, 41),
                    setOf(27, 36, 37, 38, 45, 46, 47, 48, 49),
                )
            }
        }
    }
})
