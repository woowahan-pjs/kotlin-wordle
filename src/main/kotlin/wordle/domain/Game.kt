package wordle.domain

import java.time.LocalDate

data class Game(val words: Words, val date: LocalDate) {

    private var _count: Int = 0
    private var _isOver: Boolean = false

    val count: Int
        get() = _count
    val isOver: Boolean
        get() = _isOver

    fun matchResult(playerWord: Word): Tiles {
        require(words.contains(playerWord)) { "[ERROR] words.txt에 있는 단어를 입력해주세요." }
        val tiles = mutableListOf<Tile>()
        for ((index, spell) in playerWord.value.withIndex()) {
            tiles.add(matchSpell(spell, index))
        }
        _count++
        val newTiles = Tiles(tiles)
        updateIsOver(newTiles)
        return newTiles
    }

    private fun updateIsOver(newTiles: Tiles) {
        if (_count >= MAX_ROUND || newTiles.isAllGreen()) {
            _isOver = true
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
