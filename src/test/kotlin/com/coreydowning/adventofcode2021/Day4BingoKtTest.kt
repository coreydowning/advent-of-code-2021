package com.coreydowning.adventofcode2021

import io.kotest.assertions.asClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class Day4BingoTest : FunSpec({
    test("BingoGame.call") {
        val board = Board(
            arrayOf(
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15,
                16, 17, 18, 19, 20,
                21, 22, 23, 24, 25,
            )
        )

        val game = BingoGame(listOf(board), emptyList())

        board.called.size shouldBe 25
        board.called.all { !it } shouldBe true

        game.call(1)

        board.called[0] shouldBe true
        board.called.slice(1 until 25).all { !it } shouldBe true

        game.call(18)

        board.called[17] shouldBe true
        board.called.slice(1..16).all { !it } shouldBe true
        board.called.slice(18..24).all { !it } shouldBe true

        (1..25).forEach { game.call(it) }
        board.called.all { it } shouldBe true
    }

    context("Board.isWinner") {
        withData(
            mapOf(
                "Row 1" to 1..5,
                "Row 2" to 6..10,
                "Row 3" to 11..15,
                "Row 4" to 16..20,
                "Row 5" to 21..25,
                "Col 1" to (1..25 step 5),
                "Col 2" to (2..25 step 5),
                "Col 3" to (3..25 step 5),
                "Col 4" to (4..25 step 5),
                "Col 5" to (5..25 step 5),
            )
        ) { winningCallRange ->
            val board = Board(
                arrayOf(
                    1, 2, 3, 4, 5,
                    6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15,
                    16, 17, 18, 19, 20,
                    21, 22, 23, 24, 25,
                )
            )
            val game = BingoGame(listOf(board), emptyList())

            board.isWinner shouldBe false

            winningCallRange.forEach(game::call)

            board.asClue { board.isWinner shouldBe true }
        }

        test("a few calls") {
            val board = Board(
                arrayOf(
                    1, 2, 3, 4, 5,
                    6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15,
                    16, 17, 18, 19, 20,
                    21, 22, 23, 24, 25,
                )
            )

            val game = BingoGame(listOf(board), emptyList())

            board.isWinner shouldBe false

            game.call(1)

            board.isWinner shouldBe false

            game.call(2)
            game.call(3)
            game.call(4)

            board.isWinner shouldBe false

            game.call(6)
            game.call(11)
            game.call(16)

            board.isWinner shouldBe false
        }
    }

    context("winning") {
        test("winning score") {
            val board = Board(
                arrayOf(
                    1, 2, 3, 4, 5,
                    6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15,
                    16, 17, 18, 19, 20,
                    21, 22, 23, 24, 25,
                )
            )

            val game = BingoGame(listOf(board), (1..25).toList())

            val winner = game.getWinningScore()

            board.asClue { winner shouldBe (6..25).sum() * 5 }
        }
    }

    context("losing") {
        test("losing score") {
            val calls =
                listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)
            val boardOne = Board(arrayOf(22, 13, 17, 11, 0, 8, 2, 23, 4, 24, 21, 9, 14, 16, 7, 6, 10, 3, 18, 5, 1, 12, 20, 15, 19))
            val boardTwo = Board(arrayOf(3,15,0,2,22,9,18,13,17,5,19,8,7,25,23,20,11,10,24,4,14,21,16,12,6))
            val boardThree = Board(arrayOf(14,21,17,24,4,10,16,15,9,19,18,8,23,26,20,22,11,13,6,5,2,0,12,3,7))
            val game = BingoGame(listOf(boardOne, boardTwo, boardThree), calls)

            val loser = game.getLosingScore()

            boardTwo.asClue { loser shouldBe 1924 }
        }
    }
})
