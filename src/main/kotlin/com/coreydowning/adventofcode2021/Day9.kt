package com.coreydowning.adventofcode2021

import kotlin.math.max

data class Heightmap(
    val input: List<Int>,
    val columns: Int,
) {
    val rows = input.size / columns

    val lowPoints = input
        .mapIndexed { pos, _ -> pos to adjacentTo(pos).map { input[it] } }
        .filter { (pos, adjacentHeights) -> adjacentHeights.all { input[pos] < it } }
        .map { (pos, _) -> pos }

    val riskLevel = lowPoints.sumOf { input[it] + 1 }

    fun adjacentTo(pos: Int): List<Int> = listOfNotNull(leftOf(pos), rightOf(pos), upOf(pos), downOf(pos))
    private fun leftOf(pos: Int): Int? = (pos - 1).takeIf { (pos % columns) > 0 }
    private fun rightOf(pos: Int): Int? = (pos + 1).takeIf { (it % columns) != 0 && it < input.size }
    private fun upOf(pos: Int): Int? = (pos - columns).takeIf { it >= 0 }
    private fun downOf(pos: Int): Int? = (pos + columns).takeIf { it < input.size }

    val basins: Set<Set<Int>> get() {
        return lowPoints.map { lowPoint ->
            val basin = mutableSetOf(lowPoint)
            val visited = mutableListOf<Int>()
            repeat(max(rows, columns)) { _ ->
                basin.addAll(basin.flatMap {
                    if (it !in visited) {
                        visited.add(it)
                        getPoints(it)
                    } else {
                        listOf(it)
                    }
                })
            }
            basin
        }.toSet()
    }

    val basinDangerScore = basins
        .sortedByDescending { it.size }
        .take(3)
        .map { it.size }
        .reduce { acc, size -> acc * size}

    fun getPoints(pos: Int): List<Int> {
            val basin = basinPointsToLeft(pos)
                .plus(basinPointsToRight(pos))
                .plus(pos)
            return basin.plus(
                basin.flatMap { p ->
                    basinPointsUp(p)
                        .plus(basinPointsDown(p))
                }
            )
    }

    fun basinPointsToLeft(pos: Int): List<Int> {
        val point = leftOf(pos)?.takeIf { input[it] < 9 }
        return point?.let {
            listOf(point)
                .plus(basinPointsToLeft(it))
        } ?: emptyList()
    }

    fun basinPointsToRight(pos: Int): List<Int> {
        val point = rightOf(pos)?.takeIf { input[it] < 9 }
        return point?.let {
            listOf(point)
                .plus(basinPointsToRight(it))
        } ?: emptyList()
    }

    fun basinPointsUp(pos: Int): List<Int> {
        val point = upOf(pos)?.takeIf { input[it] < 9 }
        return point?.let {
            listOf(point)
                .plus(basinPointsUp(it))
        } ?: emptyList()
    }

    fun basinPointsDown(pos: Int): List<Int> {
        val point = downOf(pos)?.takeIf { input[it] < 9 }
        return point?.let {
            listOf(point)
                .plus(basinPointsDown(it))
        } ?: emptyList()
    }

}