package wordle.domain

class GameResult() {

    private var gameResult = mutableListOf<Tiles>()

    val getGameResult: List<Tiles>
        get() = gameResult

    fun add(tiles: Tiles) {
        this.gameResult.add(tiles)
    }
}
