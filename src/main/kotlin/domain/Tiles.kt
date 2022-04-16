package domain

import domain.exception.checkTileSize

data class Tiles(val tiles: List<Tile>) : List<Tile> by tiles {
    constructor(words: String) : this(words.map(::Tile))

    init {
        checkTileSize(tiles.size == REQUIRE_TILE_SIZE) { ERROR_TILE_SIZE_MSG }
    }

    fun equals(tile: Tile, index: Int): Boolean = this.tiles[index] == tile

    fun countOf(tile: Tile): Int = this.tiles.count { it == tile }

    companion object {
        private const val ERROR_TILE_SIZE_MSG = "타일은 5개로 구성되어야 합니다."
        private const val REQUIRE_TILE_SIZE = 5
    }
}
