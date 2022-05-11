package wordle.domain

import java.time.LocalDate

data class Game(val words: Words, val date: LocalDate) {

    private var isOver: Boolean = false

    val getIsOver: Boolean
            get() = isOver

    private var count: Int = 0

    val getCount: Int
        get() = count

    fun matchResult(playerWord: Word): Tiles {
        require(words.contains(playerWord)) { "[ERROR] words.txt에 있는 단어를 입력해주세요." }
        val tiles = mutableListOf<Tile>()
        for ((index, spell) in playerWord.value.withIndex()) {
            tiles.add(matchSpell(spell, index))
        }
        count++
        val newTiles = Tiles(tiles)
        updateIsOver(newTiles)
        return newTiles
    }

    private fun updateIsOver(newTiles: Tiles) {
        if (count >= MAX_ROUND || newTiles.isAllGreen()) {
            isOver = true
        }
    }

    private fun matchSpell(spell: Char, index: Int): Tile {
        val answer = words.findAnswer(date)
        if (answer.sameIndexAndSpell(index, spell)) {
            return Tile.GREEN
        }
        if (answer.contains(spell)) {
            return Tile.YELLOW
        }
        return Tile.GRAY
    }

    companion object {
        private const val MAX_ROUND = 6
    }
}
