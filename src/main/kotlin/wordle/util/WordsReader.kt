package wordle.util

import wordle.domain.Word
import kotlin.io.path.Path
import kotlin.io.path.readLines

object WordsReader {

    fun getWords(): List<Word> {
        return Path("src/main/resources/words.txt").readLines().map { Word(it) }
    }
}
