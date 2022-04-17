package wordle.domain

import wordle.utils.Creator
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Answer(private val creator: Creator, private val position: Position) {
    val answer: Word
        get() = createAnswer()

    fun createAnswer(): Word {
        return creator.createWords().findAnswer(position)
    }

    companion object {
        private val SUBTRACT_DATE_FOR_ANSWER = LocalDate.of(2021, 6, 19)
        val ANSWER_POSITION = ChronoUnit.DAYS.between(SUBTRACT_DATE_FOR_ANSWER, LocalDate.now()).toInt()
    }
}
