package domain

import domain.PlayResult.*

class Game(
    private val wordsPool: WordsPool
) {
    val allMatchResults: List<MatchResults>
        get() = _allMatchResults

    private val answer: Answer = Answer(wordsPool.getTodayWords())
    private val _allMatchResults = mutableListOf<MatchResults>()

    fun play(tiles: Tiles): PlayResult {
        if (!isPlaying() || !wordsPool.contains(tiles)) {
            return FAILED
        }

        _allMatchResults.add(answer.match(tiles))

        return SUCCEEDED
    }

    fun isPlaying(): Boolean = allMatchResults.size < MAX_TRY_COUNT && allMatchResults.none { it.isAllGreens() }

    companion object {
        private const val MAX_TRY_COUNT = 6
    }
}
