package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Words(private val values: List<Word>) {

    private val answer: Word = findAnswer()
    private var answerMap: MutableMap<Char, Int> = HashMap()

    private fun findAnswer(): Word {
        val nowDate: LocalDate = LocalDate.now()
        val days: Int = ChronoUnit.DAYS.between(STANDARD_DATE, nowDate).toInt()
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
        val result: MutableList<Tile> = ArrayList()
        repeat(5) { result.add(Tile.GRAY) }

        repeat(5) { i -> result[i] = findTileBySameCheck(word, i) }
        repeat(5) { i -> result[i] = findTileByContainCheck(result, word, i) }
        return result
    }

    private fun initAnswerMap(): MutableMap<Char, Int> {
        val answerMap: MutableMap<Char, Int> = HashMap()
        answer.value.forEach {
            answerMap.putIfAbsent(it, 0)
            answerMap.computeIfPresent(it) { _, v -> v + 1 }
        }
        return answerMap
    }

    private fun findTileBySameCheck(word: Word, index: Int): Tile {
        if (answer.isSameChar(word, index)) {
            calculateAnswerMap(word.value[index])
            return Tile.GREEN
        }
        return Tile.GRAY
    }

    private fun findTileByContainCheck(result: List<Tile>, word: Word, index: Int): Tile {
        if (result[index] == Tile.GREEN) {
            return Tile.GREEN
        }
        if (answerMap.keys.contains(word.value[index])) {
            calculateAnswerMap(word.value[index])
            return Tile.YELLOW
        }
        return Tile.GRAY
    }

    private fun calculateAnswerMap(key: Char) {
        answerMap.computeIfPresent(key) { _, v -> v - 1 }
        if (answerMap[key] == 0) {
            answerMap.remove(key)
        }
    }

    companion object {
        private val STANDARD_DATE = LocalDate.of(2021, 6, 19)
    }
}
