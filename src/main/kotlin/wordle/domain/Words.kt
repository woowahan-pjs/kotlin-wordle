package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Words(private val values: List<Word>, today: LocalDate = LocalDate.now()) {

    private val answer: Word = findAnswer(today)
    private var answerMap: MutableMap<Char, Int> = mutableMapOf()

    private fun findAnswer(date: LocalDate): Word {
        val standardDate: LocalDate = LocalDate.of(2021, 6, 19)
        val days: Int = ChronoUnit.DAYS.between(standardDate, date).toInt()
        return values[days % values.size]
    }

    fun contains(word: Word): Boolean = values.contains(word)

    fun isCorrect(word: Word): Boolean {
        return word == answer
    }

    fun check(word: Word): List<Tile> {
        answerMap = initAnswerMap().toMutableMap()
        val result = MutableList(WORD_SIZE) { Tile.GRAY }

        repeat(WORD_SIZE) { result.putTileIfSame(word, it) }
        repeat(WORD_SIZE) { result.putTileIfContains(word, it) }
        return result
    }

    private fun initAnswerMap(): Map<Char, Int> {
        return answer.value
            .groupBy { it }
            .mapValues { it.value.count() }
    }

    private fun MutableList<Tile>.putTileIfSame(word: Word, index: Int) {
        if (answer.isSameChar(word, index)) {
            calculateAnswerMap(word.value[index])
            this[index] = Tile.GREEN
        }
    }

    private fun MutableList<Tile>.putTileIfContains(word: Word, index: Int) {
        if (this[index] != Tile.GREEN && answerMap.containsKey(word.value[index])) {
            calculateAnswerMap(word.value[index])
            this[index] = Tile.YELLOW
        }
    }

    private fun calculateAnswerMap(key: Char) {
        answerMap.computeIfPresent(key) { _, v -> v - 1 }
        if (answerMap[key] == 0) {
            answerMap.remove(key)
        }
    }

    companion object {
        private const val WORD_SIZE = 5
    }
}
