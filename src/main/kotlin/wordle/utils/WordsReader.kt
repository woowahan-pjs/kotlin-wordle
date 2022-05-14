package wordle.utils

import wordle.domain.Word
import java.io.FileReader

object WordsReader {

    fun getWords(): List<Word> {
        val path = "src/main/resources/words.txt"
        val reader = FileReader(path)
        return reader.readLines().map { Word(it) }
    }
}
