package com.coreydowning.adventofcode2021

object Day1SonarSweep {
    fun sweep(readings: List<Int>): Int =
        readings.zipWithNext().fold(0) { acc, (first, second) ->
            if (second > first) {
                acc + 1
            } else {
                acc
            }
        }
}