package wordle.domain

class GameResult(size: Int) {

    var gameResult: MutableList<Tiles> = ArrayList(size)
        private set

    fun add(tiles: Tiles) {
        this.gameResult.add(tiles)
    }
}
