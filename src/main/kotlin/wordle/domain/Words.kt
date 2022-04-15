package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

// 전략패턴 필요
class Words(private val words: List<Word>) {

    fun findAnswer(): Word {
        return words[(findAnswerPosition() % words.size).toInt()]
    }

    private fun findAnswerPosition() = ChronoUnit.DAYS.between(SUBTRACT_DATE_FOR_ANSWER, LocalDate.now())

    fun contains(value: String): Boolean {
        return words.contains(Word(value))
    }

    companion object {
        private val SUBTRACT_DATE_FOR_ANSWER = LocalDate.of(2021, 6, 19)
    }
}
