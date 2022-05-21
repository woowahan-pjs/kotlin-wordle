package wordle.domain

class Game(private val words: Words) {

    constructor(words: List<Word>) : this(Words(words))

    private var _count: Int = 0
    private var _results: MutableList<List<Tile>> = mutableListOf()

    val count: Int
        get() = _count

    val results: List<List<Tile>>
        get() = _results

    fun isGameOver(answer: Word): Boolean {
        return _count == MAX_GAME_COUNT || words.isCorrect(answer)
    }

    fun match(answer: Word) {
        require(words.contains(answer)) { "등록된 단어가 아닙니다." }
        results.add(words.check(answer))
        count++
    }

    companion object {
        const val MAX_GAME_COUNT = 6
    }
}
