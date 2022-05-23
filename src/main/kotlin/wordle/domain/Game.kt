package wordle.domain

import java.time.LocalDate

class Game(private val words: Words, val maxGameCount: Int) {

    constructor(
        words: List<Word>,
        date: LocalDate = LocalDate.now(),
        maxGameCount: Int = DEFAULT_MAX_GAME_COUNT
    ) : this(Words(words, date), maxGameCount)

    var count: Int = 0
        private set

    private var _results: MutableList<List<Tile>> = mutableListOf()

    val results: List<List<Tile>>
        get() = _results

    fun isGameOver(answer: Word): Boolean {
        return count == maxGameCount || words.isCorrect(answer)
    }

    fun match(answer: Word) {
        require(words.contains(answer)) { "등록된 단어가 아닙니다." }
        _results.add(words.check(answer))
        count++
    }

    companion object {
        const val DEFAULT_MAX_GAME_COUNT = 6
    }
}
