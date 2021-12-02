package com.coreydowning.adventofcode2021

data class Position(
    val horizontal: Int = 0,
    val depth: Int = 0,
    val aim: Int = 0,
) {
    val multiplied = horizontal * depth

    fun forward(units: Int): Position = copy(
        horizontal = this.horizontal + units,
        depth = this.depth + (units * aim),
    )

    fun down(units: Int): Position = copy(aim = aim + units)

    fun up(units: Int): Position = copy(aim = aim - units)

    fun command(name: String, units: Int): Position =
        when (name) {
            "forward" -> forward(units)
            "down" -> down(units)
            "up" -> up(units)
            else -> throw Exception("invalid command")
        }

    fun commands(commands: List<Pair<String, Int>>): Position =
        commands.fold(this) { currentPosition, (cmdName, units) -> currentPosition.command(cmdName, units) }

    companion object {
        val initial = Position()
    }
}