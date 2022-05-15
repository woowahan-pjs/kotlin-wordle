package wordle.domain

data class GameResult(val gameResult: MutableList<Tiles> = mutableListOf()) {

    fun add(tiles: Tiles) {
        this.gameResult.add(tiles)
    }
}
