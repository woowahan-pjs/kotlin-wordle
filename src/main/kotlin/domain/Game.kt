package domain

class Game(
    private val input: Input,
    private val wordsRepository: WordsRepository
) {
    fun start() {
        val answer = Answer(wordsRepository.getTodayWords())

        (BEGIN_INDEX..MAX_TRY_COUNT).forEach { index ->
            val tiles = input.read()

            if (wordsRepository.exists(tiles)) {
                val match = answer.match(tiles)

                if (match.isCorrect()) {
                    return
                }
            }
        }
    }

    companion object {
        const val BEGIN_INDEX = 1
        const val MAX_TRY_COUNT = 6
    }
}
