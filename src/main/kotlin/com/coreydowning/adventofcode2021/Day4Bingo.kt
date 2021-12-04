package com.coreydowning.adventofcode2021

//data class BingoNumber(
//    val number: Int,
//    var called: Boolean = false,
//)

data class Board(
    val grid: Array<Int>,
    val called: Array<Boolean> = (grid.indices).map { false }.toTypedArray(),
) {
    companion object {
        val winningRanges = listOf(
            0..4,
            5..9,
            10..14,
            15..19,
            20..24,
            0..24 step 5,
            1..24 step 5,
            2..24 step 5,
            3..24 step 5,
            4..24 step 5,
        )
    }

    val isWinner: Boolean get() = winningRanges.any { range -> range.all { index -> called[index] } }
    val unmarkedNumbers: List<Int> get() = grid.filterIndexed { index, _ -> !called[index] }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if (!grid.contentEquals(other.grid)) return false
        if (!called.contentEquals(other.called)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = grid.contentHashCode()
        result = 31 * result + called.contentHashCode()
        return result
    }
}

data class BingoGame(
    val boards: List<Board>,
    val calls: List<Int>,
) {
    fun call(n: Int) {
        boards.forEach { board -> board.grid.indexOf(n).takeIf { it != -1 }?.let { board.called[it] = true } }
    }

    fun getWinningScore(): Int {
        for (n in calls) {
            call(n)
            boards.firstOrNull { board -> board.isWinner }?.let { return it.unmarkedNumbers.sum() * n }
        }
        return -1
    }
}