package wordle.utils

import wordle.domain.Word
import wordle.domain.Words
import java.io.File

class WordsCreator : Creator {

    override fun createWords(): Words {
        val wordsFile = read().readLines()
        return Words(wordsFile.map { Word(it) })
    }

    private fun read(): File {
        return File(ClassLoader.getSystemResource(ANSWERS_TEXT_PATH).file)
    }

    companion object {
        private const val ANSWERS_TEXT_PATH = "words.txt"
        val WORDS = WordsCreator().createWords()
    }
}
