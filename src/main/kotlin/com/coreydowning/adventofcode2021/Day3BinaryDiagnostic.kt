package com.coreydowning.adventofcode2021

object Part1 {
    fun calculate(readings: List<String>): BinaryDiagnostics {
        val bitLength = readings.first().length
        val gammaRate = gammafy(bitLength, readings, '1', '0')
        val epsilonRate = gammafy(bitLength, readings, '0', '1')

        val readingsAsInts = readings.map { it.toInt(2) }

        val oxygenRate = oxygen(readings, 0, bitLength)

        val co2Rate = co2(readings, 0, bitLength)

        return BinaryDiagnostics(
            gammaRate = gammaRate,
            epsilonRate = epsilonRate,
            oxygenGeneratorRating = oxygenRate,
            co2ScrubberRating = co2Rate,
        )
    }

    tailrec fun oxygen(readings: List<String>, index: Int, bitCount: Int): Int {
        return if (readings.isEmpty()) {
            throw IllegalArgumentException("readings cannot be empty")
        } else if (readings.size == 1) {
            readings.single().toInt(2)
        } else if (readings.size == 2) {
            oxygen(
                readings.filter { it[index] == '1' },
                index + 1,
                bitCount
            )
        } else {
            val shft = bitCount - index - 1
            val gammafied = gammafy(bitCount, readings, '1', '0')
            oxygen(readings.filter { reading ->
                gammafied.ushr(shft) == reading.toInt(2).ushr(shft)
            }, index + 1, bitCount)
        }
    }

    tailrec fun co2(readings: List<String>, index: Int, bitCount: Int): Int {
        return if (readings.isEmpty()) {
            throw IllegalArgumentException("readings cannot be empty")
        } else if (readings.size == 1) {
            readings.single().toInt(2)
        } else {
            val gammafied = (0 until bitCount)
                .map { idx -> readings.map { reading -> reading.getOrNull(idx) == '0' } }
                .map {
                    val binaryDigits = (it.toBooleanArray())
                    binaryDigits.count { it } <= binaryDigits.count { !it }
                }
                .map {
                    if (it) {
                        '0'
                    } else {
                        '1'
                    }
                }
                .joinToString("")
            co2(readings.filter { reading ->
                gammafied[index] == reading[index]
            }, index + 1, bitCount)
        }
    }

    private fun gammafy(bitLength: Int, readings: List<String>, truthyVal: Char, falseyVal: Char) =
        (0 until bitLength)
            .map { index -> readings.map { reading -> reading.getOrNull(index) == '1' } }
            .map { gammaOf(*(it.toBooleanArray())) }
            .map {
                if (it) {
                    truthyVal
                } else {
                    falseyVal
                }
            }
            .joinToString("")
            .toInt(2)

}

fun gammaOf(vararg binaryDigits: Boolean) = binaryDigits.count { it } >= binaryDigits.count { !it }

data class BinaryDiagnostics(
    val gammaRate: Int,
    val epsilonRate: Int,
    val oxygenGeneratorRating: Int,
    val co2ScrubberRating: Int,
) {
    val lifeSupportRating = oxygenGeneratorRating * co2ScrubberRating
}