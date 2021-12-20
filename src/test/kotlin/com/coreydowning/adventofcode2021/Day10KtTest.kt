package com.coreydowning.adventofcode2021


import io.kotest.assertions.asClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Day10KtTest : FunSpec({
    context("sample input") {
        val lines = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
        """.trimIndent().lines()

        withData(listOf(
            "{([(<{}[<>[]}>{[]{[(<()>" to 1197,
            "[[<[([]))<([[{}[[()]]]" to 3,
            "[{[{({}]{}}([{[{{{}}([]" to 57,
            "[<(<(<(<{}))><([]([]()" to 3,
            "<{([([[(<>()){}]>(<<{{" to 25137,
        )) { (line, score) ->
            Line.parseInput(line).asClue {
                it.isCorrupt shouldBe true
                it.corruptedScore shouldBe score
            }
        }

        withData(listOf(
            "[({(<(())[]>[[{[]{<()<>>" to 288957,
            "[(()[<>])]({[<{<<[]>>(" to 5566,
            "(((({<>}<{<{<>}{[]{[]{}" to 1480781,
            "{<[[]]>}<{[{[{[]{()[[[]" to 995444,
            "<{([{{}}[<[[[<>{}]]]>[]]" to 294,
        )) { (line, score) ->
            Line.parseInput(line).asClue {
                it.isCorrupt shouldBe false
                it.autocompleteScore shouldBe score
            }
        }

        test("score for file") {
            File.parseInput(lines).asClue {
                it.corruptedScore shouldBe 26397
                it.autocompleteScore shouldBe 288957
            }
        }
    }
})
