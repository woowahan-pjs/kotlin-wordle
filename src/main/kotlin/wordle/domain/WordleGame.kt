package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class WordleGame(
    private val words: WordleWords,
    private var count: Int = 6
) {
    private var isCorrect: Boolean = false
    private val results = mutableListOf<WordleGameResult>()

    fun isEnd(): Boolean = count <= 0 || isCorrect

    fun getTodaysWord(today: LocalDate): Word {
        val index = (ChronoUnit.DAYS.between(STANDARD_DATE, today).toInt()) % words.size
        return words.getWord(index)
    }

    fun play(answerWord: Word, word: Word, resultEvent: (List<WordleGameResult>) -> Unit) {
        validateWord(word)
        val result = WordleComparator().getTileColors(answerWord, word)
        isCorrect = result.isCorrect()
        results.add(result)
        resultEvent(results)
        count--
    }

    private fun validateWord(word: Word) {
        require(words.contains(word)) { "단어장에 단어가 없습니다" }
    }

    companion object {
        private val STANDARD_DATE = LocalDate.of(2021, 6, 19)
    }
}
