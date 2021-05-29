
import java.lang.StringBuilder

class Board(val columns: Int, val rows: Int) {

    /**
     * Our board representation
     */
    val grid: Array<Array<Cell>> = Array(columns) { i -> Array(rows) { j -> Cell(i, j, this) } }

    class Cell(val m_x: Int, val m_y: Int, val board: Board?, var m_val: State = State.EMPTY) {
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

        fun coords(): Pair<Int, Int> = Pair(m_x, m_y)

        /**
         * @return true if a token would fall through to the cell below
         */
        fun fallThrough(): Boolean {
            return board?.let {
                !(m_y >= board.rows - 1 || board.grid[m_x][m_y+1].m_val != State.EMPTY)
            } ?: false
        }

        fun below(): Cell? {
            return board?.let {
                if (m_y >= board.rows) {
                    null
                }
                board.grid[m_x][m_y + 1]
            }
        }

        override fun toString() = m_val.toString()
    }

    fun cell(x: Int, y: Int): Cell = grid[x][y]

    /**
     *
     * @return Was it a legal move or not
     */
    fun drop(x: Int, player: Cell.State): Boolean {
        if (!grid[x][0].fallThrough()) {
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