import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GameTests {

    /*
        🟡 🟡 🟡 🟡 🟡 🟡 🟡
        🟡 🟡 🟡 🟡 🟡 🟡 🟡
        🟡 🟡 🟡 🟡 🟡 🟡 🟡
        🟡 🟡 🟡 🟡 🟡 🟡 🟡
        🟡 🟡 🟡 🟡 🟡 🟡 🟡
        🟣 🟣 🟣 🟣 🟡 🟡 🟡
     */
    @Test
    fun test1() {
        val game = Game (4, 7, 6)

        game.playerOneMove(0)
        assertFalse (game.cellWin(0,5))
        game.playerOneMove(1)
        assertFalse (game.cellWin(0,5))
        game.playerOneMove(2)
        assertFalse (game.cellWin(0,5))
        game.playerOneMove(3)

        println (game.board)

        assertTrue (game.cellWin(0,5))
        assertTrue (game.cellWin(1,5))
        assertTrue (game.cellWin(2,5))
        assertTrue (game.cellWin(3,5))
    }
}