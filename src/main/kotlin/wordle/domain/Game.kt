package wordle.domain

class Game(private val words: Words) {

class Game(private val words: Words, private val maxGameCount: Int) {

    constructor(
        words: List<Word>,
        maxGameCount: Int = DEFAULT_MAX_GAME_COUNT
    ) : this(Words(words), maxGameCount)

    private var _count: Int = 0
    private var _results: MutableList<List<Tile>> = mutableListOf()

    val count: Int
        get() = _count

    val results: List<List<Tile>>
        get() = _results

    fun isGameOver(answer: Word): Boolean {
        return _count == maxGameCount || words.isCorrect(answer)
    }

    fun match(answer: Word) {
        require(words.contains(answer)) { "등록된 단어가 아닙니다." }
        results.add(words.check(answer))
        count++
    }

    companion object {
        const val DEFAULT_MAX_GAME_COUNT = 6
    }
}
