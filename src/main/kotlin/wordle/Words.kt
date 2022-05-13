package wordle

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Words(private val values: List<Word>) {

    private val answer: Word = findAnswer()

    private fun findAnswer(): Word {
        val date: LocalDate = LocalDate.now()
        val standardDate: LocalDate = LocalDate.of(2021, 6, 19)
        val days: Int = ChronoUnit.DAYS.between(standardDate, date).toInt()
        return values[days % values.size]
    }

    fun contains(word: Word): Boolean {
        return values.contains(word)
    }

    fun isCorrect(word: Word): Boolean {
        return word == answer
    }
}
