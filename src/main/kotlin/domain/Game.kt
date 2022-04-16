package domain

class Game(
    private val input: Input,
    private val output: Output,
    private val wordsRepository: WordsPool
) {
    fun start() {
        val answer = Answer(wordsRepository.getTodayWords())
        var tryCount = 0

        while (tryCount < MAX_TRY_COUNT) {
            val tiles = input.read()

            if (!wordsRepository.exists(tiles)) {
                continue
            }

            val match = answer.match(tiles)

            output.write(match)
            tryCount++

            if (match.isCorrect()) {
                return
            }
        }
    }

    companion object {
        const val MAX_TRY_COUNT = 6
    }
}
