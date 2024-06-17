package wordle.domain

import wordle.exception.ExceptionMessage
import wordle.loader.isContainsDictionaryWord
import java.time.LocalDate

typealias TodayWord = Word

class Word(private val word: List<Letter>) : List<Letter> by word {
    fun letters(): String {
        return word.joinToString("", transform = Letter::value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Word

        return word == other.word
    }

    override fun hashCode(): Int {
        return word.hashCode()
    }
}

const val WORD_LENGTH = 5

fun Word(word: String): Word {
    check(word.isNotBlank()) { ExceptionMessage.WORD_NOT_BLANK.message }
    check(word.length == WORD_LENGTH) { ExceptionMessage.INVALID_WORD_LENGTH.message }
    check(isContainsDictionaryWord(word)) { ExceptionMessage.WORD_NOT_FOUND.message }

    return Word(word.toCharArray().map { Letter(it) })
}

fun TodayWord(today: LocalDate): TodayWord {
    return Word(extractWordleWord(today))
}
