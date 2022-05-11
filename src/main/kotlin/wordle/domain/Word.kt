package wordle.domain

import java.io.File

private const val WORD_LENGTH = 5

data class Word(private var word: String) {
    init {
        require(word.length == WORD_LENGTH) { "단어는 5글자여야 합니다." }
        require(contains(word)) { "유효하지 않은 단어입니다." }
        word = word.lowercase()
    }

    fun compareByIndex(other: Word, myIndex: Int, otherIndex: Int = myIndex): Boolean {
        return word[myIndex] == other.word[otherIndex]
    }

    companion object {
        private val CACHE: List<String> = File("src/main/resources/words.txt")
            .readLines()

        fun contains(word: String): Boolean {
            return CACHE.contains(word)
        }

        fun findWordByDay(day: Int): Word {
            return Word(CACHE[day % CACHE.size])
        }
    }
}