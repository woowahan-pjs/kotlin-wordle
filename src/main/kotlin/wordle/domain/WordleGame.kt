package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class WordleGame(
    private val words: WordleWords,
) {
    private val results = mutableListOf<WordleGameResult>()
    var isCorrect: Boolean = false
        private set
    var count: Int = 0
        private set

    fun isEnd(): Boolean {
        return count >= MAX_ROUND || isCorrect
    }

    fun getTodaysWord(today: LocalDate): Word {
        val index = (ChronoUnit.DAYS.between(STANDARD_DATE, today).toInt()) % words.size
        return words.getWord(index)
    }

    fun play(answerWord: Word, word: Word): List<WordleGameResult> {
        validateWord(word)
        val result = WordleComparator().getTileColors(answerWord, word)
        isCorrect = result.isCorrect()
        results.add(result)
        count++

        return results.toList()
    }

    private fun validateWord(word: Word) {
        require(words.contains(word)) { "단어장에 단어가 없습니다" }
    }

    companion object {
        const val MAX_ROUND = 6
        private val STANDARD_DATE = LocalDate.of(2021, 6, 19)
    }
}
