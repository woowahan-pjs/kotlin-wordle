package wordle.domain

class Tiles(private val tiles: List<Tile>) : List<Tile> by tiles {

    fun isWinner(): Boolean {
        val count = tiles.count { it == Tile.GREEN }

        if (count == TOTAL_TILE_COUNT) {
            return true
        }

        return false
    }

    companion object {
        private const val TOTAL_TILE_COUNT = 5
    }
}
