package com.coreydowning.adventofcode2021

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PositionTest : FunSpec({
    xcontext("part one") {

        test("forward") {
            Position.initial.forward(5) shouldBe Position(5, 0)
            Position.initial.forward(5).forward(5) shouldBe Position(10, 0)
            Position(5, 5).forward(5) shouldBe Position(10, 5)
        }

        test("down") {
            Position.initial.down(5) shouldBe Position(0, 5)
            Position.initial.down(5).down(5) shouldBe Position(0, 10)
            Position(5, 5).down(5) shouldBe Position(5, 10)
        }

        test("up") {
            Position(0, 10).up(5) shouldBe Position(0, 5)
            Position(0, 10).up(5).up(5) shouldBe Position(0, 0)
            Position(5, 5).up(5) shouldBe Position(5, 0)
        }

        test("command") {
            Position.initial.command("forward", 5) shouldBe Position(5, 0)
            Position.initial.command("down", 5) shouldBe Position(0, 5)
            Position(0, 5).command("up", 5) shouldBe Position(0, 0)
        }

        test("commands") {
            Position.initial.commands(
                listOf(
                    "forward" to 5,
                    "down" to 5,
                    "forward" to 8,
                    "up" to 3,
                    "down" to 8,
                    "forward" to 2,
                )
            ) shouldBe Position(15, 10)
        }

    }

    context("part two") {

        test("forward") {
            Position.initial.forward(5) shouldBe Position(5, 0, 0)
            Position.initial.forward(5).forward(5) shouldBe Position(10, 0, 0)
            Position(5, 5, 0).forward(5) shouldBe Position(10, 5, 0)

            Position(5, 5, 1).forward(5) shouldBe Position(10, 10, 1)
            Position(5, 5, 2).forward(5) shouldBe Position(10, 15, 2)
            Position(5, 15, -2).forward(5) shouldBe Position(10, 5, -2)
        }

        test("down") {
            Position.initial.down(5) shouldBe Position(0, 0, 5)
            Position.initial.down(5).down(5) shouldBe Position(0, 0, 10)
            Position(5, 5, 5).down(5) shouldBe Position(5, 5, 10)
        }

        test("up") {
            Position(0, 0, 10).up(5) shouldBe Position(0, 0, 5)
            Position(0, 0, 10).up(5).up(5) shouldBe Position(0, 0, 0)
            Position(5, 5, 5).up(5) shouldBe Position(5, 5, 0)
        }

        test("command") {
            Position.initial.command("forward", 5) shouldBe Position(5, 0, 0)
            Position.initial.command("down", 5) shouldBe Position(0, 0, 5)
            Position(0, 0,5).command("up", 5) shouldBe Position(0, 0, 0)
        }

        test("commands") {
            Position.initial.commands(
                listOf(
                    "forward" to 5,
                    "down" to 5,
                    "forward" to 8,
                    "up" to 3,
                    "down" to 8,
                    "forward" to 2,
                )
            ) shouldBe Position(15, 60, 10)
        }

    }

})
