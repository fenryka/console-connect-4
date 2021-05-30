import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BoardTests {
    @Test
    fun basicBoardTest1() {
        val board = Board(2,5)
        board.cell(0,4).apply {
            assertEquals(0, coords().first)
            assertEquals(4, coords().second)
            assertFalse { fallThrough() }
        }
        board.cell(1,3).apply {
            assertEquals(1, coords().first)
            assertEquals(3, coords().second)
            assertTrue { fallThrough() }
        }
    }

    @Test
    fun b() {
        val board = Board (2, 2)
        println (board)
    }

    @Test
    fun dropper1() {
        val board = Board (1, 5)

        Board.Cell.State.EMPTY.run {
            assertEquals("$this\n$this\n$this\n$this\n$this", board.toString())
        }

        board.drop(0, Board.Cell.State.PURPLE)

        arrayOf (Board.Cell.State.EMPTY, Board.Cell.State.PURPLE).let { c ->
            assertEquals("${c[0]}\n${c[0]}\n${c[0]}\n${c[0]}\n${c[1]}", board.toString())
        }

        board.drop(0, Board.Cell.State.PURPLE)

        arrayOf (Board.Cell.State.EMPTY, Board.Cell.State.PURPLE).let { c ->
            assertEquals("${c[0]}\n${c[0]}\n${c[0]}\n${c[1]}\n${c[1]}", board.toString())
        }

        board.drop(0, Board.Cell.State.GREEN)

        arrayOf (Board.Cell.State.EMPTY, Board.Cell.State.PURPLE, Board.Cell.State.GREEN).let { c ->
            assertEquals("${c[0]}\n${c[0]}\n${c[2]}\n${c[1]}\n${c[1]}", board.toString())
        }
    }

    @Test
    fun dropper2() {
        val board = Board (3, 3)

        println (board)
        println()
        board.drop(0, Board.Cell.State.GREEN)
        println (board)
        println()
        board.drop(1, Board.Cell.State.GREEN)
        println (board)
        println()
        board.drop(1, Board.Cell.State.PURPLE)
        println (board)
        println()
    }

    /**
     * Test we stop dropping if the column is full
     */
    @Test
    fun dropper3() {
        val board = Board (1, 5)
        assertTrue { board.drop(0, Board.Cell.State.GREEN) }
        assertTrue { board.drop(0, Board.Cell.State.GREEN) }
        assertTrue { board.drop(0, Board.Cell.State.GREEN) }
        assertTrue { board.drop(0, Board.Cell.State.GREEN) }
        assertTrue { board.drop(0, Board.Cell.State.GREEN) }
        assertFalse { board.drop(0, Board.Cell.State.GREEN) }
    }
}