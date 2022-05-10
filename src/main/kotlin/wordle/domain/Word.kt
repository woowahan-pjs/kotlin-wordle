package wordle.domain

import java.io.File

class Word(val word: String) {
    init {
        require(word.length == 5) { "단어는 5글자여야 합니다." }
        require(contains(word)) { "유효하지 않은 단어입니다." }
    }

    companion object {
        val CACHE: List<String> = File("src/main/resources/words.txt")
            .readLines()

        fun contains(word: String): Boolean {
            return CACHE.contains(word)
        }
    }
}