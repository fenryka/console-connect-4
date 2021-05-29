
import kotlin.test.Test

class CellTests {
    @Test
    fun greenCell() {
        val c = Board.Cell (0, 0, null).apply { m_val = Board.Cell.State.GREEN }
        println (c)
    }

    @Test
    fun purpleCell() {
        val c = Board.Cell (0, 0, null).apply { m_val = Board.Cell.State.PURPLE }
        println (c)
    }

    @Test
    fun emptyCell() {
        val c = Board.Cell (0, 0, null).apply { m_val = Board.Cell.State.EMPTY }
        println (c)
    }
}