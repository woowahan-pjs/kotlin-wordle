package domain

import domain.MatchResult.INCORRECT
import domain.MatchResult.MISSING

class Answer(val tiles: Tiles) {
    init {
        require(tiles.size == REQUIRE_TILE_SIZE) { ERROR_TILE_SIZE_MSG }
    }

    fun match(other: Tiles): MatchResults {
        val result: Array<MatchResult> = arrayOf(INCORRECT, INCORRECT, INCORRECT, INCORRECT, INCORRECT)
        val countOfTile: MutableMap<Tile, Int> = tiles.groupingBy { it }.eachCount().toMutableMap()

        fillGreens(result, other, countOfTile)
        fillYellows(result, other, countOfTile)

        return MatchResults(result.toList())
    }

    private fun fillGreens(
        result: Array<MatchResult>,
        other: Tiles,
        countOfTile: MutableMap<Tile, Int>
    ) {
        other.mapIndexed { index, tile ->
            fillGreen(tile, index, result, countOfTile)
        }
    }
    private fun fillYellows(
        result: Array<MatchResult>,
        other: Tiles,
        countOfTile: MutableMap<Tile, Int>
    ) {
        other.mapIndexed { index, tile ->
            fillYellow(tile, index, result, countOfTile)
        }
    }

    private fun fillGreen(
        tile: Tile,
        index: Int,
        result: Array<MatchResult>,
        countOfTile: MutableMap<Tile, Int>
    ) {
        if (this.tiles.equals(tile, index)) {
            result[index] = MatchResult.CORRECT

            countOfTile[tile] = countOfTile[tile]!!.dec()
        }
    }

    private fun fillYellow(
        tile: Tile,
        index: Int,
        result: Array<MatchResult>,
        countOfTile: MutableMap<Tile, Int>
    ) {
        val count = countOfTile[tile] ?: EMPTY

        if (result[index] == INCORRECT && count > EMPTY) {
            result[index] = MISSING

            countOfTile[tile] = count.dec()
        }
    }

    companion object {
        const val ERROR_TILE_SIZE_MSG = "타일은 5개로 구성되어야 합니다."
        const val REQUIRE_TILE_SIZE = 5
        const val EMPTY = 0

        fun of(words: String): Answer = Answer(Tiles.of(words))
    }
}
