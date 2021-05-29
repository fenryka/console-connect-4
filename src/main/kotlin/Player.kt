class Player (val name: String, val move: (Int) -> Boolean)

class Players (val players: Array<Player>) {
    var current = 0

    fun activePlayer() = players[current]

    fun next() {
        current = if (current == 0) 1 else 0
    }
}