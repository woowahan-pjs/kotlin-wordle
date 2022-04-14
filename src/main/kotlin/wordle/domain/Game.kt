package wordle.domain

class Game(tryCount: TryCount, private val answer: Word) {
    private var _tryCount: TryCount = tryCount

    fun play(input: Word): Boolean {
        val inputChars = input.value.toCharArray()
        val wordMatcher = WordMatcher(answer)
        if (countMatches(inputChars, wordMatcher) == WINNER_MATCHING_COUNT) {
            return true
        }

        _tryCount = _tryCount.increase()
        return false
    }

    fun tryCount(): TryCount {
        return _tryCount
    }

    private fun countMatches(inputChars: CharArray, wordMatcher: WordMatcher): Int {
        var sumCount = 0
        inputChars.forEachIndexed { index, it ->
            if (wordMatcher.match(it.toString(), index) == Tile.GREEN) {
                sumCount++
            }
        }

        return sumCount
    }

    companion object {
        const val WINNER_MATCHING_COUNT = 5
    }
}
