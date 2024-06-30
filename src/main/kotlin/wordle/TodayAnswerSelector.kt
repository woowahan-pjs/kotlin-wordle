package wordle

import wordle.domain.AnswerSelector
import java.time.LocalDate

class TodayAnswerSelector(private val today: LocalDate): AnswerSelector {
    private val baseDate = LocalDate.of(2021, 6, 19)

    override fun findIndex(maxSize: Int): Int {
        return today.toEpochDay().minus(baseDate.toEpochDay()).toInt() % maxSize
    }
}