package domain

import domain.MatchResult.INCORRECT
import domain.MatchResult.MISSING

class Answer(val tiles: List<Tile>) {
    init {
        require(tiles.size == REQUIRE_TILE_SIZE) { ERROR_TILE_SIZE_MSG }
    }

    fun match(other: Tiles): MatchResults {
        val result: Array<MatchResult> = arrayOf(INCORRECT, INCORRECT, INCORRECT, INCORRECT, INCORRECT)
        val countOfTile: MutableMap<Tile, Int> = tiles.groupingBy { it }.eachCount().toMutableMap()

        fillGreen(result, other, countOfTile)
        fillYellow(result, other, countOfTile)

        return MatchResults(result.toList())
    }

    private fun fillGreen(
        result: Array<MatchResult>,
        other: Tiles,
        countOfTile: MutableMap<Tile, Int>
    ) {
        this.tiles.mapIndexed { index, tile ->
            if (other.equals(tile, index)) {
                result[index] = MatchResult.CORRECT

                countOfTile[tile] = countOfTile[tile]!!.dec()
            }
        }
    }

    private fun fillYellow(
        result: Array<MatchResult>,
        other: Tiles,
        countOfTile: MutableMap<Tile, Int>
    ) {
        other.mapIndexed { index, tile ->
            val count = countOfTile[tile] ?: EMPTY

            if (result[index] == INCORRECT && count > EMPTY) {
                result[index] = MISSING

                countOfTile[tile] = count.dec()
            }
        }
    }

    companion object {
        const val ERROR_TILE_SIZE_MSG = "타일은 5개로 구성되어야 합니다."
        const val REQUIRE_TILE_SIZE = 5
        const val EMPTY = 0

        fun of(words: String): Answer = Answer(words.map { Tile(it) })
    }
}
