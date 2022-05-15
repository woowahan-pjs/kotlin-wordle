package wordle.domain

class GameResult() {

    var gameResult: MutableList<Tiles> = ArrayList()
        private set

    fun add(tiles: Tiles) {
        this.gameResult.add(tiles)
    }
}
