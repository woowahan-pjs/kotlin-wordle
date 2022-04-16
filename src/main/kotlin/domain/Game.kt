package domain

class Game(private val words: Words) {
    private val answer: Answer = Answer(words.getTodayWords())
    private var tryCount = 0

    fun progress(input: Input): MatchResults {
        val tiles = input.read()
        checkTryCount()
        checkWord(tiles)
        tryCount++
        return answer.match(tiles)
    }

    private fun checkWord(tiles: Tiles) {
        if (!words.exists(tiles)) {
            throw IllegalArgumentException(NOT_FOUND_WORD)
        }
    }

    private fun checkTryCount() {
        if (tryCount == MAX_TRY_COUNT) {
            throw IllegalArgumentException(EXHAUST_TRY_COUNT)
        }
    }

    companion object {
        private const val MAX_TRY_COUNT = 6
        private const val NOT_FOUND_WORD = "존재하지 않는 단어입니다"
        private const val EXHAUST_TRY_COUNT = "기회 6번을 다 써버렸씁니다."
    }
}
