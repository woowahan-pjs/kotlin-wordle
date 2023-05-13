package wordle.domain

class WordleGame(
    private val answerWord: Word
) {
    private val results = mutableListOf<WordleGameResult>()
    var isCorrect: Boolean = false
        private set
    var count: Int = 0
        private set

    fun isEnd(): Boolean {
        return count >= MAX_ROUND || isCorrect
    }

    fun play(word: Word): List<WordleGameResult> {
        val result = WordleComparator().getTileColors(answerWord, word)
        isCorrect = result.isCorrect()

        results.add(result)
        count++

        return results.toList()
    }

    companion object {
        const val MAX_ROUND = 6
    }
}
