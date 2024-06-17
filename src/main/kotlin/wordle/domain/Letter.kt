package wordle.domain

import wordle.exception.WordleExceptionCode

data class Letter(private val value: Char) {
    init {
        check(isAlphabetOrMatchMarker()) { WordleExceptionCode.LETTER_INVALID_CHARACTER_TYPE.message }
    }

    private fun isAlphabetOrMatchMarker(): Boolean = isAlphabet() || isMatchMarker()

    private fun isAlphabet(): Boolean = value in ALPHABET

    private fun isMatchMarker(): Boolean = value == MATCH_MARKER

    companion object {
        private const val MATCH_MARKER = '#'
        private val ALPHABET = ('a'..'z').toSet()
    }
}
