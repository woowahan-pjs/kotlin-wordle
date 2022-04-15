package wordle.domain

import java.io.File

class Answers {
    val answer: Word
        get() = createAnswer()

    private fun createAnswer() = WORDS.findAnswer()

    companion object {
        val WORDS = createWords()
        private const val ANSWERS_TEXT_PATH = "./words.txt"

        private fun createWords(): Words {
            val wordsFile = getResourceText().readLines()
            return Words(wordsFile.map { Word(it) })
        }

        private fun getResourceText(): File {
            return File(ClassLoader.getSystemResource(ANSWERS_TEXT_PATH).file)
        }
    }
}
