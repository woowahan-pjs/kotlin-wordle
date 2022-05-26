package wordle.domain

data class Tiles(val tiles: List<Tile>) {

    fun isAllGreen(): Boolean {
        return tiles.all { it.isGreen() }
    }
}
