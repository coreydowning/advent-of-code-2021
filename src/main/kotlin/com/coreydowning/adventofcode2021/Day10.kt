package com.coreydowning.adventofcode2021

fun Char.closes(openingChar: Char): Boolean = when (this) {
    ')' -> openingChar == '('
    ']' -> openingChar == '['
    '}' -> openingChar == '{'
    '>' -> openingChar == '<'
    else -> false
}

fun Char.corruptedScore(): Int = when (this) {
    ')' -> 3
    ']' -> 57
    '}' -> 1197
    '>' -> 25137
    else -> 0
}

fun Char.autocomplete(): Char = when (this) {
    '(' -> ')'
    '[' -> ']'
    '{' -> '}'
    '<' -> '>'
    else -> '?'
}

fun Char.autocompleteScore(): Long = when (this) {
    ')' -> 1
    ']' -> 2
    '}' -> 3
    '>' -> 4
    else -> 0
}

data class Line(
    val line: String,
    val isCorrupt: Boolean = false,
    val corruptedScore: Int = 0,
    val autocompleteScore: Long = 0,
) {
    companion object {
        fun parseInput(line: String): Line {
            val chunkStack = mutableListOf<Char>()
            line.forEach { c ->
                when (c) {
                    '[', '(', '<', '{', -> chunkStack.add(c)
                    else -> {
                        val lastOpen = chunkStack.removeLast()
                        val closes = c.closes(lastOpen)
                        if (!closes) {
                            return Line(line, isCorrupt = true, corruptedScore = c.corruptedScore())
                        }
                    }
                }
            }
            val autocompleteScore: Long = chunkStack.reversed().fold(0L) { acc, c ->
                (acc * 5L) + c.autocomplete().autocompleteScore()
            }
            return Line(line, autocompleteScore = autocompleteScore)
        }
    }
}

data class File(val lines: List<Line>) {
    val corruptedScore = lines.sumOf { it.corruptedScore }
    val uncorruptLines = lines.filterNot { it.isCorrupt }
    val autocompleteScore = uncorruptLines.sortedBy { it.autocompleteScore }[uncorruptLines.size / 2].autocompleteScore

    companion object {
        fun parseInput(lines: List<String>): File = File(lines.map { Line.parseInput(it) })

    }
}