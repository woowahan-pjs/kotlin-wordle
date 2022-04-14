package wordle.domain

import wordle.controller.getResourceText
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Answers {

    companion object {
        private const val ANSWERS_TEXT_PATH = "./words.txt"
        val WORDS = createWords()
        val ANSWER = createAnswer()

        private fun createWords(): List<Word> {
            val wordsFile = getResourceText(ANSWERS_TEXT_PATH)

            val words = mutableListOf<Word>()
            wordsFile.forEachLine {
                words.add(Word(it))
            }

            return words
        }

        private fun createAnswer(): Word {
            return WORDS[(createAnswerPosition() % WORDS.size).toInt()]
        }

        private fun createAnswerPosition() = ChronoUnit.DAYS.between(LocalDate.of(2021, 6, 19), LocalDate.now())
    }
}
