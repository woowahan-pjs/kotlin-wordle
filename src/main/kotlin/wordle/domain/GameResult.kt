package wordle.domain

class GameResult() {

    private val _gameResult = mutableListOf<Tiles>()

    val gameResult: List<Tiles>
        get() = _gameResult

    fun add(tiles: Tiles) {
        this._gameResult.add(tiles)
    }
}
