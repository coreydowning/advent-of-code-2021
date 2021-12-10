package com.coreydowning.adventofcode2021

data class Heightmap(
    val input: List<Int>,
    val columns: Int,
) {
    val lowPoints = input
        .mapIndexed { pos, _ -> pos to adjacentTo(pos).map{ input[it] } }
        .filter { (pos, adjacentHeights) -> adjacentHeights.all { input[pos] < it } }
        .map { (pos, _) -> pos }

    val riskLevel = lowPoints.sumOf { input[it]+1 }

    fun adjacentTo(pos: Int): List<Int> = listOfNotNull(leftOf(pos), rightOf(pos), upOf(pos), downOf(pos))
    private fun leftOf(pos: Int): Int? = (pos - 1).takeIf { (pos % columns) > 0 }
    private fun rightOf(pos: Int): Int? = (pos + 1).takeIf { (it % columns) != 0 }
    private fun upOf(pos: Int): Int? = (pos - columns).takeIf { it >= 0 }
    private fun downOf(pos: Int): Int? = (pos + columns).takeIf { it < input.size }
}