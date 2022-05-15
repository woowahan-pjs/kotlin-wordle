package wordle.domain

class Game(private val words: Words) {

    constructor(words: List<Word>) : this(Words(words))

    var count: Int = 0
        private set

    var results: MutableList<List<Tile>> = ArrayList()
        private set

    fun isGameOver(answer: Word): Boolean {
        return count == MAX_GAME_COUNT || words.isCorrect(answer)
    }

    fun match(answer: Word) {
        require(words.contains(answer)) { "등록된 단어가 아닙니다." }
        count++
        results.add(words.check(answer))
    }

    companion object {
        private const val MAX_GAME_COUNT = 6
    }
}
