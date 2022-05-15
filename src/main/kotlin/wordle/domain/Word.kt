package wordle.domain

import java.io.File

private const val WORD_LENGTH = 5

data class Word(private val word: String) {
    init {
        require(word.length == WORD_LENGTH) { "단어는 5글자여야 합니다." }
        require(isLowerCase(word)) { "단어는 소문자로 이루어져야 합니다." }
        require(contains(word)) { "유효하지 않은 단어입니다." }
    }

    private fun isLowerCase(value: String): Boolean {
        return value.all { it.isLowerCase() }
    }

    fun compareByIndex(other: Word, myIndex: Int, otherIndex: Int = myIndex): Boolean {
        return word[myIndex] == other.word[otherIndex]
    }

    fun getWord(): String {
        return word
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
