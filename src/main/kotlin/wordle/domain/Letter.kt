package wordle.domain

import wordle.exception.ExceptionMessage.INVALID_LETTER

data class Letter(private val value: Char) {
    init {
        check(isAlphabetOrMatchMarker()) { INVALID_LETTER.message }
    }

    fun changeMatchMarker(): Letter = MATCH_MARKER_LETTER

    fun value(): String {
        return value.toString()
    }

    private fun isAlphabetOrMatchMarker(): Boolean = isAlphabet() || isMatchMarker()

    private fun isAlphabet(): Boolean = (value in ALPHABET)

    private fun isMatchMarker(): Boolean = (value == MATCH_MARKER)

    companion object {
        private const val MATCH_MARKER = '#'
        private val ALPHABET = ('a'..'z').toSet()
        private val MATCH_MARKER_LETTER = Letter(MATCH_MARKER)
    }
}
