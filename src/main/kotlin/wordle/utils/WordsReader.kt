package wordle.utils

import wordle.domain.Word
import java.io.FileReader

private const val WORDS_PATH = "src/main/resources/words.txt"

object WordsReader {

    fun getWords(): List<Word> {
        val reader = FileReader(WORDS_PATH)
        return reader.readLines().map { Word(it) }
    }
}
