package domain

class Game(
    private val input: Input,
    private val output: Output,
    private val wordsRepository: WordsRepository
) {
    fun start() {
        val answer = Answer(wordsRepository.getTodayWords())
        var inputCount = 0

        while (inputCount < MAX_TRY_COUNT) {
            val tiles = input.read()

            if (wordsRepository.exists(tiles)) {
                val match = answer.match(tiles)

                output.write(match)
                inputCount++

                if (match.isCorrect()) {
                    return
                }
            }
        }
    }

    companion object {
        const val MAX_TRY_COUNT = 6
    }
}
