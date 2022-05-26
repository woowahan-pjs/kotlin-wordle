package wordle.domain

class GameResult() {

    var gameResult: MutableList<Tiles> = ArrayList(SIZE)
        private set

    fun add(tiles: Tiles) {
        this.gameResult.add(tiles)
    }

    companion object {
        private const val SIZE = 5
    }
}
