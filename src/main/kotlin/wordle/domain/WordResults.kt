package wordle.domain

class WordResults(
    private val results: MutableList<WordResult> = mutableListOf(),
    private val tryCount: TryCount = TryCount(),
) {
    val attemptCount get() = tryCount.attempts

    fun addResults(result: WordResult) {
        tryCount.minus()
        results.add(result)
    }

    fun isContinuousGame(): Boolean = !isSuccessGame() && tryCount.isRemainder()

    fun isSuccessGame(): Boolean = results.any(WordResult::isSuccessGame)

    fun wordResults(): List<List<LetterMatch>> = results.map(WordResult::matches)
}
