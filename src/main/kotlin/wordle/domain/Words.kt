package wordle.domain

import java.time.LocalDate

class Words(private val values: List<Word>) {

    private val answer: Word = findAnswer()
    private var answerMap: MutableMap<Char, Int> = mutableMapOf()

    private fun findAnswer(): Word {
        val days = TODAY.compareTo(STANDARD_DATE)
        return values[days % values.size]
    }

    fun contains(word: Word): Boolean {
        return values.contains(word)
    }

    fun isCorrect(word: Word): Boolean {
        return word == answer
    }

    fun check(word: Word): List<Tile> {
        answerMap = initAnswerMap()
        return DEFAULT_TILES.markGreen(word).markYellow(word)
    }

    private fun initAnswerMap(): MutableMap<Char, Int> {
        return answer.value
            .groupBy { it }
            .mapValues { it.value.count() }
            .toMutableMap()
    }

    private fun List<Tile>.markGreen(word: Word): List<Tile> {
        return List(size) { index -> grayOrGreen(word, index) }
    }

    private fun grayOrGreen(word: Word, index: Int): Tile {
        return if (answer.isSameChar(word, index)) {
            calculateAnswerMap(word.value[index])
            Tile.GREEN
        } else {
            Tile.GRAY
        }
    }

    private fun List<Tile>.markYellow(word: Word): List<Tile> {
        return mapIndexed { index, tile -> existOrYellow(tile, word, index) }
    }

    private fun existOrYellow(tile: Tile, word: Word, index: Int): Tile {
        return if (tile != Tile.GREEN && answerMap.containsKey(word.value[index])) {
            calculateAnswerMap(word.value[index])
            Tile.YELLOW
        } else {
            tile
        }
    }

    private fun calculateAnswerMap(key: Char) {
        answerMap.computeIfPresent(key) { _, v -> v - 1 }
        if (answerMap[key] == 0) {
            answerMap.remove(key)
        }
    }

    companion object {
        private val TODAY = LocalDate.now()
        private val STANDARD_DATE = LocalDate.of(2021, 6, 19)
        private val DEFAULT_TILES = List(5) { Tile.GRAY }
    }
}
