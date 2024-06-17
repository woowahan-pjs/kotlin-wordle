package wordle.domain

import wordle.exception.ExceptionMessage

data class WordResult(private val result: MutableList<LetterMatch> = MutableList(WORD_LENGTH) { LetterMatch.ABSENT }) {
    init {
        check(result.size == WORD_LENGTH) { ExceptionMessage.INVALID_WORD_RESULT_LENGTH.message }
    }

    fun isSuccessGame() = result.all { matchType -> matchType == LetterMatch.CORRECT }

    fun changeMatchType(
        index: Int,
        matchType: LetterMatch,
    ) {
        result[index] = matchType
    }

    fun matches(): List<LetterMatch> = result.toList()
}
