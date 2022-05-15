package wordle.domain

import java.time.LocalDate

data class Game(val words: Words, val date: LocalDate) {

    var count: Int = 0
        private set
    var isOver: Boolean = false
        private set
    val maxRound: Int = MAX_ROUND

    fun matchResult(playerWord: Word): Tiles {
        require(words.contains(playerWord)) { "[ERROR] words.txt에 있는 단어를 입력해주세요." }
        val tiles = playerWord.value.withIndex()
            .mapIndexedNotNull{index, tile -> matchSpell(tile.value, index)}
        count++
        val newTiles = Tiles(tiles)
        updateIsOver(newTiles)
        return newTiles
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

    private fun updateIsOver(newTiles: Tiles) {
        if (count >= MAX_ROUND || newTiles.isAllGreen()) {
            isOver = true
        }
    }

    companion object {
        const val MAX_ROUND = 6
    }
}
