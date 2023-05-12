package wordle.domain

class WordleGame(private val wordPicker: WordPicker) {
    private val history: MutableList<List<Tile>> = mutableListOf()

    private val todayAnswer: Answer by lazy { Answer(wordPicker.pick()) }

    fun playGame(
        userGuess: String,
    ): List<List<Tile>> {
        val judgeResult = todayAnswer.judge(userGuess)

        history.add(judgeResult)

        return history.toList()
    }

    fun isOver(): Boolean {
        val greenCount = history.last().count { it == Tile.GREEN }

        return (greenCount == ALL_CORRECT_COUNT) or (history.size == NO_MORE_COUNT)
    }

    companion object {
        private const val ALL_CORRECT_COUNT = 5
        private const val NO_MORE_COUNT = 6
    }
}
