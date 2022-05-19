package wordle.domain

import java.time.LocalDate

data class Game(val words: Words, val date: LocalDate) {

    var count: Int = 0
        private set
    var isOver: Boolean = false
        private set
    val maxRound: Int
        get() = MAX_ROUND

    fun matchResult(playerWord: Word): Tiles {
        require(words.contains(playerWord)) { "[ERROR] words.txt에 있는 단어를 입력해주세요." }
        val answer = words.findAnswer(date)
        val tiles = playerWord.value.withIndex()
            .mapIndexedNotNull { index, value ->
                matchSpell(value.value, index, answer)
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

    private fun matchSpell(spell: Char, index: Int, answer: Word): Tile {
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
