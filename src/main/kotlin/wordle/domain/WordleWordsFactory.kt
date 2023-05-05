package wordle.domain

import java.io.File

class WordleWordsFactory {

    fun generate(): WordleWords {
        val lines = File(FILE_PATH).readLines()
        return WordleWords(lines.map(::Word))
    }

    companion object {
        private const val FILE_PATH = "src/main/resources/words.txt"
    }
}
