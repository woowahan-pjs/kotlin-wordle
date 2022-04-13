package domain

data class Tiles(val tiles: List<Tile>) : List<Tile> by tiles {
    init {
        require(tiles.size == REQUIRE_TILE_SIZE) { ERROR_TILE_SIZE_MSG }
    }

    fun equals(tile: Tile, index: Int): Boolean = this.tiles[index] == tile

    fun countOf(tile: Tile): Int = this.tiles.count { it == tile }

    companion object {
        const val ERROR_TILE_SIZE_MSG = "타일은 5개로 구성되어야 합니다."
        const val REQUIRE_TILE_SIZE = 5

        fun of(words: String): Tiles = Tiles(words.map { Tile(it) })
    }
}
