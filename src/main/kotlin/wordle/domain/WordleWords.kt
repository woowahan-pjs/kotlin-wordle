package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class WordleWords(
    private val words: List<Word>
) {

    fun getTodaysWord(today: LocalDate): Word {
        val index = (ChronoUnit.DAYS.between(STANDARD_DATE, today).toInt()) % words.size
        return words[index]
    }

    fun contains(word: Word): Boolean {
        if (words.contains(word)) return true
        else throw IllegalArgumentException("단어장에 포함된 단어여야 합니다")
    }

    companion object {
        private val STANDARD_DATE = LocalDate.of(2021, 6, 19)
    }
}
