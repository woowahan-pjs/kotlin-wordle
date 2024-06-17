package wordle.domain

data class WordResult(private val result: MutableList<LetterMatch> = MutableList(WORD_LENGTH) { LetterMatch.ABSENT }) {
    fun changeMatchType(
        index: Int,
        matchType: LetterMatch,
    ) {
        result[index] = matchType
    }

    fun isCorrectMatchIndex(index: Int): Boolean = result[index] == LetterMatch.CORRECT
}
