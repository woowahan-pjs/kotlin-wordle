package wordle.domain

import wordle.exception.WordleExceptionCode.WORD_RESULT_INVALID_LENGTH

data class WordResult(private val result: MutableList<LetterMatch> = MutableList(WORD_LENGTH) { LetterMatch.ABSENT }) {
    init {
        check(result.size == WORD_LENGTH) { WORD_RESULT_INVALID_LENGTH.message }
    }

    fun changeMatchType(
        index: Int,
        matchType: LetterMatch,
    ) {
        result[index] = matchType
    }

    fun isCorrectLetterMatch(index: Int): Boolean = result[index] == LetterMatch.CORRECT

    fun isSuccessfulWordResult() = result.all { matchType -> matchType == LetterMatch.CORRECT }

    fun matches(): List<LetterMatch> = result.toList()
}
