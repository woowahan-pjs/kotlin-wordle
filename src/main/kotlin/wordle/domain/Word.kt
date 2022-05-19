package wordle.domain

import java.io.File

private const val WORD_LENGTH = 5

data class Word(private val word: String) {

    init {
        require(word.length == WORD_LENGTH) { "단어는 5글자여야 합니다." }
        require(word.isLowerCase()) { "단어는 소문자로 이루어져야 합니다." }
        require(word.containsInWord()) { "유효하지 않은 단어입니다." }
    }

    private fun String.isLowerCase(): Boolean {
        return this.all { it.isLowerCase() }
    }

    fun compareByIndex(other: Word, myIndex: Int, otherIndex: Int = myIndex): Boolean {
        return word[myIndex] == other.word[otherIndex]
    }

    companion object {
        private val CACHE: List<String> = File("src/main/resources/words.txt")
            .readLines()

        fun String.containsInWord(): Boolean {
            return CACHE.contains(this)
        }

        fun findWordByDay(day: Int): Word {
            return Word(CACHE[day % CACHE.size])
        }
    }
}
