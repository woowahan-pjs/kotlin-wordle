package wordle.domain

import java.time.LocalDate

class Words(private val values: List<Word>, today: LocalDate = LocalDate.now()) {

    private val answer: Word = findAnswer(today)
    private var answerMap: MutableMap<Char, Int> = mutableMapOf()

    private fun findAnswer(date: LocalDate): Word {
        val days = date.compareTo(STANDARD_DATE)
        return values[days % values.size]
    }

    fun contains(word: Word): Boolean = values.contains(word)

    fun isCorrect(word: Word): Boolean {
        return word == answer
    }

    fun check(word: Word): List<Tile> {
        answerMap = initAnswerMap().toMutableMap()
        return DEFAULT_RESULT.markGreen(word).markYellow(word)
    }

    private fun initAnswerMap(): Map<Char, Int> {
        return answer.value
            .groupBy { it }
            .mapValues { it.value.count() }
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
        const val WORD_SIZE = 5
        private val DEFAULT_RESULT = List(WORD_SIZE) { Tile.GRAY }
        private val STANDARD_DATE = LocalDate.of(2021, 6, 19)
    }
}
