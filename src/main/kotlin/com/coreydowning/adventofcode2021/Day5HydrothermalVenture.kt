package com.coreydowning.adventofcode2021

data class Point(
    val x: Int,
    val y: Int,
)

data class LineSegment(
    val points: Pair<Point, Point>,
) {
    fun covers(point: Point): Boolean =
        (isHorizontal && point.y == points.first.y && inHorizontalLine(point)) ||
        (isVertical && point.x == points.first.x && inVerticalLine(point))

    private fun inVerticalLine(point: Point) =
        (points.first.y..points.second.y).contains(point.y) ||
        (points.second.y..points.first.y).contains(point.y)

    private fun inHorizontalLine(point: Point) =
        (points.first.x..points.second.x).contains(point.x) ||
        (points.second.x..points.first.x).contains(point.x)

    private val isHorizontal = points.first.y == points.second.y
    private val isVertical = points.first.x == points.second.x
}

data class NearbyVents(
    val vents: List<LineSegment>,
) {
    fun howManyCover(point: Point): Int = vents.count { it.covers(point) }

    fun howManyDangerousPoints(): Int =
        (yMin..yMax).map { y -> (xMin..xMax).map { x -> howManyCover(Point(x, y)) } }
            .flatten().count { it > 1 }

    private val allXPoints = vents.map { line -> line.points.first.x }
        .union(vents.map { line -> line.points.second.x })

    private val allYPoints = vents.map { line -> line.points.first.y }
        .union(vents.map { line -> line.points.second.y })

    private val xMin = allXPoints.minOrNull() ?: 0
    private val yMin = allYPoints.minOrNull() ?: 0
    private val xMax = allXPoints.maxOrNull() ?: 0
    private val yMax = allYPoints.maxOrNull() ?: 0
}