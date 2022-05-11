package wordle.domain

import java.io.File

class WordsResponse(val path: String) {

    val words: List<Word>
        get() = getWords(path)

    companion object {
        private fun getWords(path: String): List<Word> {
            return File(ClassLoader.getSystemResource(path).file).readLines()
                .map { Word(it) }
        }
    }
}