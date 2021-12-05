package com.coreydowning.adventofcode2021

data class Point(
    val x: Int,
    val y: Int,
)

private infix fun Int.rangeTo(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}

data class LineSegment(
    val points: Pair<Point, Point>,
) {
    fun covers(point: Point): Boolean = point in pointsInLine

    private val xPoints = (points.first.x rangeTo points.second.x)
    private val yPoints = (points.first.y rangeTo points.second.y)

    private val isHorizontal = points.first.y == points.second.y
    private val isVertical = points.first.x == points.second.x

    val pointsInLine = if (isHorizontal) {
        xPoints.map { x -> Point(x, points.first.y) }
    } else if (isVertical) {
        yPoints.map { y -> Point(points.first.x, y) }
    } else {
        xPoints.zip(yPoints).map { (x, y) -> Point(x, y) }
    }
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