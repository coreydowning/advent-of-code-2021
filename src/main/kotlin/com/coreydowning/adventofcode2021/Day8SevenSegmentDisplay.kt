package com.coreydowning.adventofcode2021

typealias SignalSet = String

data class Entry(
    val signalPatterns: List<SignalSet>,
    val output: List<SignalSet>,
) {
    val ones = output.count { it.length == 2 }
    val fours = output.count { it.length == 4 }
    val sevens = output.count { it.length == 3 }
    val eights = output.count { it.length == 7 }

    val signalMapper: Map<Char, Char> get() {
        val one = signalPatterns.first { it.length == 2 }
        val four = signalPatterns.first { it.length == 4 }
        val seven = signalPatterns.first { it.length == 3 }
        val eight = signalPatterns.first { it.length == 7 }

        val a = seven.first { c -> c !in one }
        val f = signalPatterns.filter { it.length == 6 }.plus(one).flatMap { it.toList() }.groupBy { it }.filterValues { it.size == 4 }.keys.first()
        val c = one.first { ch -> ch !in "$a$f" }
        val b = four.filterNot { ch -> ch in one }.first { ch -> signalPatterns.filter { it.length == 6 }.all { ch in it } }
        val d = four.filterNot { ch -> ch in "$b$c$f" }.first()
        val g = signalPatterns.filter { it.length == 6 }.flatMap { it.toList() }.filterNot { ch -> ch in "$a$b$c$d$f" }.groupBy { it }.filterValues { it.size == 3 }.keys.first()
        val e = eight.filterNot { ch -> ch in "$a$b$c$d$f$g" }.first()

        return mapOf(
            a to 'a',
            b to 'b',
            c to 'c',
            d to 'd',
            e to 'e',
            f to 'f',
            g to 'g',
        )
    }

    val digitMapping: Map<Set<Char>, String> = mapOf(
        "abcefg".toSet() to "0",
        "cf".toSet() to "1",
        "acdeg".toSet() to "2",
        "acdfg".toSet() to "3",
        "bcdf".toSet() to "4",
        "abdfg".toSet() to "5",
        "abdfeg".toSet() to "6",
        "acf".toSet() to "7",
        "abcdefg".toSet() to "8",
        "abcdfg".toSet() to "9",
    )

    val outputValue = output.map { digit ->
        digit.map { signalMapper[it] }.joinToString("").toSet()
    }.map { digitMapping[it] }.joinToString("").toInt()

    companion object {
        fun fromInput(input: String): Entry {
            val (patterns, outputs) = input.split(" | ").map{ it.split(' ') }
            return Entry(
                patterns,
                outputs,
            )
        }
    }
}

data class EntrySet(
    val entries: List<Entry>
) {
    val ones = entries.sumOf { entry -> entry.ones }
    val fours = entries.sumOf { entry -> entry.fours }
    val sevens = entries.sumOf { entry -> entry.sevens }
    val eights = entries.sumOf { entry -> entry.eights }

    val easyDigits = ones + fours + sevens + eights
    val sumOfOutputs = entries.sumOf { entry -> entry.outputValue }

    companion object {
        fun fromInput(input: List<String>): EntrySet =
            EntrySet(input.map { Entry.fromInput(it) })
    }
}