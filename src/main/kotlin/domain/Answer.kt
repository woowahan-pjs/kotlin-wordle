package domain

import domain.MatchResult.GRAY
import domain.MatchResult.YELLOW

class Answer(val tiles: List<Tile>) {
    init {
        require(tiles.size == 5) { "타일은 5개로 구성되어야 합니다." }
    }

    fun match(other: Tiles): List<MatchResult> {
        val result: Array<MatchResult> = arrayOf(GRAY, GRAY, GRAY, GRAY, GRAY)
        val countOfTile: MutableMap<Tile, Int> = tiles.groupingBy { it }.eachCount().toMutableMap()

        fillGreen(result, other, countOfTile)
        fillYellow(result, other, countOfTile)

        return result.toList()
    }

    private fun fillGreen(
        result: Array<MatchResult>,
        other: Tiles,
        countOfTile: MutableMap<Tile, Int>
    ) {
        this.tiles.mapIndexed { index, tile ->
            if (other.equals(tile, index)) {
                result[index] = MatchResult.GREEN

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
            val count = countOfTile[tile] ?: 0

            if (result[index] == GRAY && count > 0) {
                result[index] = YELLOW

                countOfTile[tile] = count.dec()
            }
        }
    }

    companion object {
        fun of(words: String): Answer = Answer(words.map { Tile(it) })
    }
}
