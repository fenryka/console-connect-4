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
                print ( "${players.activePlayer().name}: ")
                players.apply {
                    activePlayer().move (readLine()!!.toInt())
                    next()
                }

                println (game.board)
            }
        }
    }
}