package com.coreydowning.adventofcode2021

import io.kotest.assertions.asClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Day5HydrothermalVentureKtTest : FunSpec({
    val horizontalLine = LineSegment(Point(1, 3) to Point(3, 3))
    val verticalLine = LineSegment(Point(3, 1) to Point(3, 3))

    context("LineSegment#covers") {
        test("horizontal lines") {
            horizontalLine.asClue {
                it.covers(Point(1, 3)) shouldBe true
                it.covers(Point(2, 3)) shouldBe true
                it.covers(Point(3, 3)) shouldBe true
                it.covers(Point(4, 3)) shouldBe false
                it.covers(Point(0, 3)) shouldBe false
                it.covers(Point(2, 2)) shouldBe false
                it.covers(Point(2, 4)) shouldBe false
            }

            val reversedPoints = LineSegment(Point(3, 3) to Point(1, 3))
            reversedPoints.asClue {
                it.covers(Point(1, 3)) shouldBe true
                it.covers(Point(2, 3)) shouldBe true
                it.covers(Point(3, 3)) shouldBe true
                it.covers(Point(4, 3)) shouldBe false
                it.covers(Point(0, 3)) shouldBe false
                it.covers(Point(2, 2)) shouldBe false
                it.covers(Point(2, 4)) shouldBe false
            }
        }

        test("vertical lines") {
            verticalLine.asClue {
                verticalLine.covers(Point(3, 1)) shouldBe true
                verticalLine.covers(Point(3, 2)) shouldBe true
                verticalLine.covers(Point(3, 3)) shouldBe true
                verticalLine.covers(Point(3, 4)) shouldBe false
                verticalLine.covers(Point(3, 0)) shouldBe false
                verticalLine.covers(Point(2, 2)) shouldBe false
                verticalLine.covers(Point(4, 2)) shouldBe false
            }

            val reversedPoints = LineSegment(Point(3, 3) to Point(3, 1))
            reversedPoints.asClue {
                reversedPoints.covers(Point(3, 1)) shouldBe true
                reversedPoints.covers(Point(3, 2)) shouldBe true
                reversedPoints.covers(Point(3, 3)) shouldBe true
                reversedPoints.covers(Point(3, 4)) shouldBe false
                reversedPoints.covers(Point(3, 0)) shouldBe false
                reversedPoints.covers(Point(2, 2)) shouldBe false
                reversedPoints.covers(Point(4, 2)) shouldBe false
            }
        }
    }

    context("NearbyVents") {
        val nearbyVents = NearbyVents(listOf(horizontalLine, verticalLine))

        withData(listOf(
            Point(0, 0) to 0,
            Point(1, 3) to 1,
            Point(2, 3) to 1,
            Point(3, 3) to 2,
            Point(3, 1) to 1,
            Point(3, 2) to 1,
        )) { (point, covered) ->
            nearbyVents.howManyCover(point) shouldBe covered
        }

        test("#howManyDangerousPoints") {
            nearbyVents.howManyDangerousPoints() shouldBe 1
        }
    }

    context("sample input") {
        val nearbyVents = NearbyVents(
            listOf(
                LineSegment(Point(0, 9) to Point(5, 9)),
                LineSegment(Point(8, 0) to Point(0, 8)),
                LineSegment(Point(9, 4) to Point(3, 4)),
                LineSegment(Point(2, 2) to Point(2, 1)),
                LineSegment(Point(7, 0) to Point(7, 4)),
                LineSegment(Point(6, 4) to Point(2, 0)),
                LineSegment(Point(0, 9) to Point(2, 9)),
                LineSegment(Point(3, 4) to Point(1, 4)),
                LineSegment(Point(0, 0) to Point(8, 8)),
                LineSegment(Point(5, 5) to Point(8, 2)),
            )
        )

        withData(listOf(
            Point(0, 0) to 0,
            Point(1, 0) to 0,
            Point(2, 0) to 0,
            Point(3, 0) to 0,
            Point(4, 0) to 0,
            Point(5, 0) to 0,
            Point(6, 0) to 0,
            Point(7, 0) to 1,
            Point(8, 0) to 0,
            Point(9, 0) to 0,

            Point(0, 1) to 0,
            Point(1, 1) to 0,
            Point(2, 1) to 1,
            Point(3, 1) to 0,
            Point(4, 1) to 0,
            Point(5, 1) to 0,
            Point(6, 1) to 0,
            Point(7, 1) to 1,
            Point(8, 1) to 0,
            Point(9, 1) to 0,

            )) { (point, covered) ->
            nearbyVents.asClue { nearbyVents.howManyCover(point) shouldBe covered }
        }

        test("#howManyDangerousPoints") {
            nearbyVents.howManyDangerousPoints() shouldBe 5
        }
    }

})
