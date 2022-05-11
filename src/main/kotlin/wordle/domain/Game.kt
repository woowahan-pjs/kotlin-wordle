package wordle.domain

import java.time.LocalDate

data class Game(val words: Words, val date: LocalDate) {

    fun matchResult(playerWord: Word): Tiles {
        require(words.contains(playerWord)) { "[ERROR] words.txt에 있는 단어를 입력해주세요." }

        val tiles = mutableListOf<Tile>()
        for ((index, spell) in playerWord.value.withIndex()) {
            tiles.add(matchSpell(spell, index))
        }
        return Tiles(tiles)
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
}
