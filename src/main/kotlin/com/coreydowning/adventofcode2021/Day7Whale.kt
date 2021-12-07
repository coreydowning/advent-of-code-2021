package com.coreydowning.adventofcode2021

import kotlin.math.absoluteValue

class CrabSubmarineAligner(
    crabSubmarines: List<Int>
) {
    val crabSubmarines = crabSubmarines.sorted()

    fun fuelToMoveToPosition(pos: Int): Int {
        return crabSubmarines.sumOf { crabSub -> (crabSub - pos).absoluteValue }
    }

    fun cheapestPosition(): Pair<Int, Int> =
        (crabSubmarines.first()..crabSubmarines.last())
            .map { it to fuelToMoveToPosition(it) }
            .minByOrNull { it.second } ?: throw Exception("no solution")
}