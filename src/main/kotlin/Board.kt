
import java.lang.StringBuilder

class Board(val columns: Int, val rows: Int) {

    /**
     * Our board representation
     */
    val grid: Array<Array<Cell>> = Array(columns) { i -> Array(rows) { j -> Cell(i, j, this) } }

    class Cell(
        private val x: Int,
        private val y: Int,
        private val board: Board?,
        var m_val: State = State.EMPTY
    ) {
        enum class State {
            EMPTY {
                override fun toString() = "🟡"
            },
            PURPLE {
                override fun toString() = "🟣"
            },
            GREEN {
                override fun toString() = "🟢"
            }
        }

        fun coords(): Pair<Int, Int> = Pair(x, y)

        /**
         * @return true if a token would fall through to the cell below
         */
        fun fallThrough(): Boolean {
            return board?.let {
                !(y >= board.rows - 1 || board.grid[x][y+1].m_val != State.EMPTY)
            } ?: false
        }

        /**
         * @return the cell below this cell in the grid
         */
        fun below(): Cell? {
            return board?.let {
                if (y >= board.rows) {
                    null
                }
                board.grid[x][y + 1]
            }
        }

        override fun toString() = m_val.toString()

        fun empty() = m_val == State.EMPTY
    }

    fun cell(x: Int, y: Int): Cell = grid[x][y]

    /**
     * Simulate dropping a piece into the board
     *
     * @return Was it a legal move or not
     */
    fun drop(x: Int, player: Cell.State): Boolean {
        if (!grid[x][0].empty()) {
            return false
        }

        var cell = grid[x][0]

        while (cell.fallThrough()) {
            cell = cell.below()!!
        }

        cell.m_val = player

        return true
    }

    override fun toString() = StringBuilder().apply {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                append(grid[j][i].toString() + if (j<columns - 1)  " " else "")
            }
            if (i < rows - 1) {
                append("\n")
            }
        }
    }.toString()

}