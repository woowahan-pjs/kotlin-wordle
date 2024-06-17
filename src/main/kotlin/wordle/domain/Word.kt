package wordle.domain

import wordle.exception.WordleExceptionCode.WORD_INVALID_LENGTH
import wordle.exception.WordleExceptionCode.WORD_IS_NOT_IN_DICTIONARY
import wordle.exception.WordleExceptionCode.WORD_NOT_ALLOW_SPACE

data class Word(val word: String) {
    init {
        check(word.isNotBlank()) { WORD_NOT_ALLOW_SPACE.message }
        check(isValidLength()) { WORD_INVALID_LENGTH.message }
        check(isDictionaryWord(word)) { WORD_IS_NOT_IN_DICTIONARY.message }
    }

    private fun isValidLength(): Boolean = word.length == WORD_LENGTH
}

const val WORD_LENGTH = 5
