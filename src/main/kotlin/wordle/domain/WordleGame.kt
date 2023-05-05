package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class WordleGame(
    private val words: WordleWords,
) {
    fun getTodaysWord(today: LocalDate): Word {
        val index = (ChronoUnit.DAYS.between(STANDARD_DATE, today).toInt()) % words.size
        return words.getWord(index)
    }

    companion object {
        private val STANDARD_DATE = LocalDate.of(2021, 6, 19)
    }
}
