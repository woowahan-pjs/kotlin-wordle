package domain

class Answer(val tiles: List<Tile>) {
    init {
        require(tiles.size == 5) { "타일은 5개로 구성되어야 합니다." }
    }

    fun match(another : Tiles) : List<MatchResult> {
        return this.tiles.mapIndexed { index, tile ->
            if (another.equals(tile, index)) {
                MatchResult.GREEN
            } else if (another.countOf(tile) > 0) {
                MatchResult.YELLOW
            } else {
                MatchResult.GRAY
            }
        }
    }

    companion object {
        fun of(words: String): Answer = Answer(words.map { Tile(it) })
    }
}
