package edu.nextstep.wordle.application.wordle

data class WordResults(
    val wordResult: List<WordResult>,
) {
    fun isSuccess(): Boolean {
        if (wordResult.isEmpty()) {
            return false
        }
        return requireNotNull(wordResult.maxByOrNull { it.round })
            .isSuccess()
    }
}
