import kotlin.math.*

class Game (val win: Int, columns: Int, rows: Int) {
    var board = Board (columns, rows)
    private val offset = win -1

    fun playerOneMove (column: Int) : Boolean = board.drop(column, Board.Cell.State.PURPLE)
    fun playerTwoMove (column: Int) : Boolean = board.drop(column, Board.Cell.State.GREEN)

    override fun toString() = board.toString()

    fun cellWin(column: Int, row: Int) : Boolean {
        println ("Cell Win $column, $row")
        val left = max (0, column - offset)
        val top = max (0, row - offset)
        val right = min (board.columns - 1, column + offset)
        val bottom = min (board.rows - 1, row + offset)

        println ("$left, $top, $right, $bottom")

        // horazontal win
        var currPlayer : Board.Cell.State? = null
        var count = 0
        for (x in left..right) {
            val v = board.grid[x][row].m_val
            if (v == Board.Cell.State.EMPTY ) {
                currPlayer = null
                count = 0
            } else {
                if (currPlayer == null) {
                    currPlayer = v
                    count = 1
                } else if (currPlayer == v) {
                    count += 1
                } else {
                    currPlayer = v
                    count = 1
                }

                if (count == win) {
                    return true
                }
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
        return false;
    }
}