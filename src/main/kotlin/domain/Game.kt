package domain

class Game(private val words: Words) {
    private val answer: Answer = Answer(words.getTodayWords())
    private var tryCount = 0

    fun progress(input: Input): MatchResults {
        val tiles = input.read()
        checkWord(tiles)
        tryCount++
        return answer.match(tiles)
    }

    fun checkToRetry() = MAX_TRY_COUNT == tryCount

    private fun checkWord(tiles: Tiles) {
        if (!words.exists(tiles)) {
            throw IllegalArgumentException(NOT_FOUND_WORD)
        }
    }

    companion object {
        private const val MAX_TRY_COUNT = 6
        private const val NOT_FOUND_WORD = "존재하지 않는 단어입니다"
    }
}
