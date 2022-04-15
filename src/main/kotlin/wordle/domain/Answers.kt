package wordle.domain

import java.io.File
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Answers {
    val answer: Word
        get() = createAnswer()

    private fun createAnswer() = WORDS[(createAnswerPosition() % WORDS.size).toInt()]

    companion object {
        val WORDS = createWords()
        private const val ANSWERS_TEXT_PATH = "./words.txt"
        private val SUBTRACT_DATE_FOR_ANSWER = LocalDate.of(2021, 6, 19)

        private fun createWords(): Words {
            val wordsFile = getResourceText().readLines()
            return Words(wordsFile.map { Word(it) })
        }

        private fun createAnswerPosition() = ChronoUnit.DAYS.between(SUBTRACT_DATE_FOR_ANSWER, LocalDate.now())

        private fun getResourceText(): File {
            return File(ClassLoader.getSystemResource(ANSWERS_TEXT_PATH).file)
        }
    }
}
