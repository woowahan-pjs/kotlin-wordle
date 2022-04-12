package domain

class Tiles(val tiles: List<Tile>) : List<Tile> by tiles {
    init {
        require(tiles.size == 5) { "타일은 5개로 구성되어야 합니다." }
    }

    fun equals(tile: Tile, index: Int): Boolean = this.tiles[index] == tile

    fun countOf(tile: Tile): Int = this.tiles.count { it == tile }

    companion object {
        fun of(words: String): Tiles = Tiles(words.map { Tile(it) })
    }
}
