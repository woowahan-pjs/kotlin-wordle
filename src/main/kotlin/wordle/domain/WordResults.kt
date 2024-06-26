package wordle.domain

class WordResults(
    private val results: MutableList<WordResult> = mutableListOf(),
    private val tryCount: TryCount = TryCount(),
) {
    val attemptCount: Int get() = tryCount.attempts

    fun addResults(result: WordResult) {
        tryCount.minus()
        results.add(result)
    }

    fun isContinuousGame(): Boolean = !isSuccessfulGame() && tryCount.isRemainder()

    fun isSuccessfulGame(): Boolean = (tryCount.attempts != 0) && results.last().isSuccessfulWordResult()

    fun wordResults(): List<List<LetterMatch>> = results.map { it.matches() }
}
