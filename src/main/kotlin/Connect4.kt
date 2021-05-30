import java.lang.NumberFormatException

class Connect4 {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val game = Game (4, 7, 6)

            val players = Players (
                arrayOf (
                    Player ("Player One", game::playerOneMove),
                    Player ("Player Two", game::playerTwoMove)))

            while (!game.won()) {
                players.apply {
                    while(true) {
                        print ( "${activePlayer().name}: ")
                        try {
                            activePlayer().move(readLine()!!.toInt())
                            break
                        } catch (e: NumberFormatException) {
                            println("A valid column number is required")
                        }
                    }
                    println (game)
                    if (game.won()) {
                        println ("${activePlayer().name} wins!!!!")
                    }
                    next()
                }
            }
        }
    }
}