import kotlin.math.*

class Game (private val win: Int, columns: Int, rows: Int) {
    private var board = Board (columns, rows)
    private val offset = win -1

    fun playerOneMove (column: Int) : Boolean = board.drop(column, Board.Cell.State.PURPLE)
    fun playerTwoMove (column: Int) : Boolean = board.drop(column, Board.Cell.State.GREEN)

    override fun toString() = board.toString()

    /**
     * Convienience class for tracking the state of a token run in the board
     * for use in determinign if someone has won
     */
    data class Counter (
        var currPlayer: Board.Cell.State? = null,
        var count : Int = 0)

    private fun count (cell: Board.Cell, counter: Counter) : Boolean {
        val v = cell.m_val

        counter.apply {
            if (v == Board.Cell.State.EMPTY) {
                currPlayer = null
                count = 0
            } else {
                when (currPlayer) {
                    null -> {
                        currPlayer = v
                        count = 1
                    }
                    v -> {
                        count += 1
                    }
                    else -> {
                        currPlayer = v
                        count = 1
                    }
                }

                if (count == win) {
                    return true
                }
            }
        }
        return false
    }

    fun cellWin(column: Int, row: Int) : Boolean {
        val left = max (0, column - offset)
        val top = max (0, row - offset)
        val right = min (board.columns - 1, column + offset)
        val bottom = min (board.rows - 1, row + offset)

        // horazontal win
        Counter().apply {
            for (x in left..right) {
                if (count(board.grid[x][row], this)) return true
            }
        }

        // vertical win
        Counter().apply {
            for (x in top..bottom) {
                if (count(board.grid[column][x], this)) return true
            }
        }

        return false
    }

    /**
     * * B *
     * A * C
     * * D *
     */
    fun won() : Boolean {
        for (column in 0 until board.columns) {
            for (row in 0 until board.rows) {
                if (board.grid[column][row].empty()) continue
                else if (cellWin(column, row)) return true
            }
        }
        return false
    }
}