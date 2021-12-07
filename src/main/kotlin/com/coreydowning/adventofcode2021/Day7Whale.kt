package com.coreydowning.adventofcode2021

import kotlin.math.absoluteValue

class CrabSubmarineAligner(
    crabSubmarines: List<Int>
) {
    val crabSubmarines = crabSubmarines.sorted()

    fun fuelToMoveToPosition(pos: Int): Long {
        return crabSubmarines
            .map { crabSub -> (crabSub - pos).absoluteValue }
            .sumOf { changeInPosition ->
                (1..changeInPosition)
                    .fold(0L) { acc: Long, next: Int -> acc + next }
            }
    }

    fun cheapestPosition(): Pair<Int, Long> =
        (crabSubmarines.first()..crabSubmarines.last())
            .map { it to fuelToMoveToPosition(it) }
            .minByOrNull { it.second } ?: throw Exception("no solution")
}