package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Words(private val words: List<Word>) {

    fun findAnswer(current: LocalDate): Word {
        val answerIndex = ChronoUnit.DAYS.between(BASE_DATE, current)
        return words[answerIndex.toInt()]
    }

    fun contains(word: Word): Boolean {
        return words.contains(word)
    }

    companion object {
        private val BASE_DATE = LocalDate.of(2021, 6, 19)
    }
}
