package wordle.domain

import wordle.exception.WordleExceptionCode.WORD_INVALID_LENGTH
import wordle.exception.WordleExceptionCode.WORD_IS_NOT_IN_DICTIONARY
import wordle.exception.WordleExceptionCode.WORD_NOT_ALLOW_SPACE

data class Word(private val word: List<Letter>)

const val WORD_LENGTH = 5

fun Word(word: String): Word {
    check(word.isNotBlank()) { WORD_NOT_ALLOW_SPACE.message }
    check(isValidLength(word)) { WORD_INVALID_LENGTH.message }
    check(isDictionaryWord(word)) { WORD_IS_NOT_IN_DICTIONARY.message }

    return Word(word.toCharArray().map { Letter(it) })
}

private fun isValidLength(word: String) = word.length == WORD_LENGTH
