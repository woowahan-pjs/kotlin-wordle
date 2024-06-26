package wordle.domain

import wordle.exception.WordleExceptionCode.LETTER_INVALID_CHARACTER_TYPE

data class Letter(private val value: Char) {
    init {
        check(isAlphabet() || isMatchMarker()) { LETTER_INVALID_CHARACTER_TYPE.message }
    }

    fun changeMatchMarker(): Letter = MATCH_MARKER_LETTER

    fun value(): String = value.toString()

    private fun isAlphabet(): Boolean = value in ALPHABET

    private fun isMatchMarker(): Boolean = value == MATCH_MARKER

    companion object {
        private const val MATCH_MARKER = '#'
        private val ALPHABET = ('a'..'z').toSet()
        private val MATCH_MARKER_LETTER = Letter(MATCH_MARKER)
    }
}
