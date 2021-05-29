class Game (val win: Int, columns: Int, rows: Int) {
    var board = Board (columns, rows)

    fun playerOneMove (column: Int) : Boolean = board.drop(column, Board.Cell.State.PURPLE)
    fun playerTwoMove (column: Int) : Boolean = board.drop(column, Board.Cell.State.GREEN)

    override fun toString() = board.toString()

    fun won() : Boolean {
        for (i in 0 until board.rows) {
            for (j in 0 until board.columns) {

            }
        }
        return false;
    }
}