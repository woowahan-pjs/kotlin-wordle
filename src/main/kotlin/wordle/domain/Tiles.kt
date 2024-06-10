package wordle.domain

@JvmInline
value class Tiles(val tiles: List<Tile>) {

    fun isWinner(): Boolean {
        val count = tiles.count { it == Tile.GREEN }

        return count == TOTAL_TILE_COUNT
    }

    companion object {
        private const val TOTAL_TILE_COUNT = 5
    }
}
