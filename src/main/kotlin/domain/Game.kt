package domain

class Game(
    private val wordsPool: WordsPool
) {
    val allMatchResults: List<MatchResults>
        get() = _allMatchResults

    private val answer: Answer = Answer(wordsPool.getTodayWords())
    private val _allMatchResults = mutableListOf<MatchResults>()

    fun play(tiles: Tiles): PlayResult {
        if (!isPlaying() || !this.wordsPool.contains(tiles)) {
            return PlayResult.FAILED
        }

        this._allMatchResults.add(this.answer.match(tiles))

        return PlayResult.SUCCEEDED
    }

    fun isPlaying(): Boolean =
        this.allMatchResults.size < MAX_TRY_COUNT && this.allMatchResults.none { it.isAllGreens() }

    companion object {
        private const val MAX_TRY_COUNT = 6
    }
}
