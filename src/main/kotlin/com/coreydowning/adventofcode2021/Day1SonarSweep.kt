package com.coreydowning.adventofcode2021

object Day1SonarSweep {
    fun sweep(readings: List<Int>, windowSize: Int = 1): Int =
        readings
            .windowed(windowSize)
            .map(List<Int>::sum)
            .zipWithNext()
            .fold(0) { acc, (first, second) ->
                if (second > first) {
                    acc + 1
                } else {
                    acc
                }
            }
}