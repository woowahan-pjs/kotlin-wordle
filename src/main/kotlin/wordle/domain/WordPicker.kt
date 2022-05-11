package wordle.domain

import java.time.LocalDate
import java.time.Period

private const val YEAR = 2021
private const val MONTH = 6
private const val DAY_OF_MONTH = 19

class WordPicker(private val today: LocalDate = LocalDate.now()) {
    fun pickTodayAnswer(): Word {
        val fixed = LocalDate.of(YEAR, MONTH, DAY_OF_MONTH)
        val day = Period.between(fixed, today).days
        return Word.findWordByDay(day)
    }
}