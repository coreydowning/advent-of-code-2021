package com.coreydowning.adventofcode2021

object Part1 {
    fun calculate(readings: List<String>): BinaryDiagnostics {
        val bitLength = readings.first().length
        val gammaRateBooleans = (0 until bitLength)
            .map { index -> readings.map { reading -> reading[index] == '1' } }
            .map { gammaOf(*(it.toBooleanArray())) }
        val gammaRate = gammaRateBooleans
            .map {
                if (it) {
                    '1'
                } else {
                    '0'
                }
            }
            .joinToString("")
            .toBigInteger(2)
            .toInt()
        val epsilonRate = gammaRateBooleans
            .map {
                if (it) {
                    '0'
                } else {
                    '1'
                }
            }
            .joinToString("")
            .toBigInteger(2)
            .toInt()
        return BinaryDiagnostics(
            gammaRate = gammaRate,
            epsilonRate = epsilonRate,
            oxygenGeneratorRating = 0,
            co2ScrubberRating = 0,
        )
    }
}

fun gammaOf(vararg binaryDigits: Boolean) = binaryDigits.count { it } >= binaryDigits.count { !it }

data class BinaryDiagnostics(
    val gammaRate: Int,
    val epsilonRate: Int,
    val oxygenGeneratorRating: Int,
    val co2ScrubberRating: Int,
)