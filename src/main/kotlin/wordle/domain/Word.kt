package wordle.domain

import java.io.File

private const val WORD_LENGTH = 5

class Word(word: String) {
    private var letters: List<Letter>

    init {
        require(word.length == WORD_LENGTH) { "단어는 5글자여야 합니다." }
        require(contains(word)) { "유효하지 않은 단어입니다." }
        letters = word.map(::Letter)
    }

    fun compareByIndex(other: Word, myIndex: Int, otherIndex: Int = myIndex): Boolean {
        return letters[myIndex] == other.letters[otherIndex]
    }

    companion object {
        private val CACHE: List<String> = File("src/main/resources/words.txt")
            .readLines()

        fun contains(word: String): Boolean {
            return CACHE.contains(word)
        }
    }
}