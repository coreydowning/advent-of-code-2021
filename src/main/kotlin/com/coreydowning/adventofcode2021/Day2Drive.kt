package com.coreydowning.adventofcode2021

data class Position(
    val horizontal: Int,
    val depth: Int,
) {
    val multiplied = horizontal * depth

    fun forward(units: Int): Position = copy(horizontal = this.horizontal + units)

    fun down(units: Int): Position = copy(depth = depth + units)

    fun up(units: Int): Position = copy(depth = depth - units)

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
        val initial = Position(0, 0)
    }
}