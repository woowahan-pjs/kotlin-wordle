package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class AnswerGenerator(private val wordPool: List<String>) {

    fun generate(date: LocalDate): Word {
        val difference = ChronoUnit.DAYS.between(OFFSET, date)
        return Word.from(wordPool[(difference % wordPool.size).toInt()])
    }

    companion object {
        private val OFFSET: LocalDate = LocalDate.of(2021, 6, 19)
    }
}
