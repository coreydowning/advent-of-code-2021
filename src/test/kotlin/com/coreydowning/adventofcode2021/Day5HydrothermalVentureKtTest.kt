package com.coreydowning.adventofcode2021

import io.kotest.assertions.asClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
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
                it.covers(Point(3, 1)) shouldBe true
                it.covers(Point(3, 2)) shouldBe true
                it.covers(Point(3, 3)) shouldBe true
                it.covers(Point(3, 4)) shouldBe false
                it.covers(Point(3, 0)) shouldBe false
                it.covers(Point(2, 2)) shouldBe false
                it.covers(Point(4, 2)) shouldBe false
            }

            val reversedPoints = LineSegment(Point(3, 3) to Point(3, 1))
            reversedPoints.asClue {
                it.covers(Point(3, 1)) shouldBe true
                it.covers(Point(3, 2)) shouldBe true
                it.covers(Point(3, 3)) shouldBe true
                it.covers(Point(3, 4)) shouldBe false
                it.covers(Point(3, 0)) shouldBe false
                it.covers(Point(2, 2)) shouldBe false
                it.covers(Point(4, 2)) shouldBe false
            }
        }

        test("diagonal line - positive slope") {
            LineSegment(Point(1, 3) to Point(3, 1)).asClue { segment ->
                Point(1, 3).asClue { segment.covers(it) shouldBe true }
                Point(2, 2).asClue { segment.covers(it) shouldBe true }
                Point(3, 1).asClue { segment.covers(it) shouldBe true }
                Point(0, 4).asClue { segment.covers(it) shouldBe false }
                Point(4, 0).asClue { segment.covers(it) shouldBe false }
            }
        }

        test("diagonal line - positive slope reversed") {
            LineSegment(Point(3, 1) to Point(1, 3)).asClue { segment ->
                Point(1, 3).asClue { segment.covers(it) shouldBe true }
                Point(2, 2).asClue { segment.covers(it) shouldBe true }
                Point(3, 1).asClue { segment.covers(it) shouldBe true }
                Point(0, 4).asClue { segment.covers(it) shouldBe false }
                Point(4, 0).asClue { segment.covers(it) shouldBe false }
            }
        }

        test("diagonal line - negative slope") {
            LineSegment(Point(1, 1) to Point(3, 3)).asClue { segment ->
                segment.covers(Point(1, 1)) shouldBe true
                segment.covers(Point(2, 2)) shouldBe true
                segment.covers(Point(3, 3)) shouldBe true
                segment.covers(Point(0, 0)) shouldBe false
                segment.covers(Point(4, 4)) shouldBe false
            }
            LineSegment(Point(3, 3) to Point(1, 1)).asClue {
                it.covers(Point(1, 1)) shouldBe true
                it.covers(Point(2, 2)) shouldBe true
                it.covers(Point(3, 3)) shouldBe true
                it.covers(Point(0, 0)) shouldBe false
                it.covers(Point(4, 4)) shouldBe false
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
        val segmentMap = mapOf(
            LineSegment(Point(0, 9) to Point(5, 9)) to listOf(
                Point(0, 9),
                Point(1, 9),
                Point(2, 9),
                Point(3, 9),
                Point(4, 9),
                Point(5, 9),
            ),
            LineSegment(Point(8, 0) to Point(0, 8)) to listOf(
                Point(8, 0),
                Point(7, 1),
                Point(6, 2),
                Point(5, 3),
                Point(4, 4),
                Point(3, 5),
                Point(2, 6),
                Point(1, 7),
                Point(0, 8),
            ),
            LineSegment(Point(9, 4) to Point(3, 4)) to listOf(
                Point(9, 4),
                Point(8, 4),
                Point(7, 4),
                Point(6, 4),
                Point(5, 4),
                Point(4, 4),
                Point(3, 4),
            ),
            LineSegment(Point(2, 2) to Point(2, 1)) to listOf(
                Point(2, 2),
                Point(2, 1),
            ),
            LineSegment(Point(7, 0) to Point(7, 4)) to listOf(
                Point(7, 0),
                Point(7, 1),
                Point(7, 2),
                Point(7, 3),
                Point(7, 4),
            ),
            LineSegment(Point(6, 4) to Point(2, 0)) to listOf(
                Point(6, 4),
                Point(5, 3),
                Point(4, 2),
                Point(3, 1),
                Point(2, 0),
            ),
            LineSegment(Point(0, 9) to Point(2, 9)) to listOf(
                Point(0, 9),
                Point(1, 9),
                Point(2, 9),
            ),
            LineSegment(Point(3, 4) to Point(1, 4)) to listOf(
                Point(3, 4),
                Point(2, 4),
                Point(1, 4),
            ),
            LineSegment(Point(0, 0) to Point(8, 8)) to listOf(
                Point(0, 0),
                Point(1, 1),
                Point(2, 2),
                Point(3, 3),
                Point(4, 4),
                Point(5, 5),
                Point(6, 6),
                Point(7, 7),
                Point(8, 8),
            ),
            LineSegment(Point(5, 5) to Point(8, 2)) to listOf(
                Point(5, 5),
                Point(6, 4),
                Point(7, 3),
                Point(8, 2),
            ),
        )
        val nearbyVents = NearbyVents(
            segmentMap.keys.toList()
        )

        withData(segmentMap.toList()) { (segment, pointsOnLine) ->
            segment.pointsInLine shouldContainExactlyInAnyOrder pointsOnLine
        }

        test("#howManyDangerousPoints") {
            nearbyVents.vents.forEach { println("$it has line of ${it.pointsInLine}")}
            nearbyVents.howManyDangerousPoints() shouldBe 12
        }
    }

})
