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
        answerMap = initAnswerMap()
        val result = MutableList(WORD_SIZE) { Tile.GRAY }

        repeat(WORD_SIZE) { putTileIfSame(result, word, it) }
        repeat(WORD_SIZE) { putTileIfContains(result, word, it) }
        return result
    }

    private fun initAnswerMap(): MutableMap<Char, Int> {
        return answer.value
            .groupBy { it }
            .mapValues { it.value.count() } as MutableMap<Char, Int>
    }

    private fun putTileIfSame(result: MutableList<Tile>, word: Word, index: Int) {
        if (answer.isSameChar(word, index)) {
            calculateAnswerMap(word.value[index])
            result[index] = Tile.GREEN
        }
    }

    private fun putTileIfContains(result: MutableList<Tile>, word: Word, index: Int) {
        if (result[index] != Tile.GREEN && answerMap.containsKey(word.value[index])) {
            calculateAnswerMap(word.value[index])
            result[index] = Tile.YELLOW
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
