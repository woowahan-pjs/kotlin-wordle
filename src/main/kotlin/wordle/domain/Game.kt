package wordle.domain

const val TOTAL_ROUNDS_NUM = 6

class Game(private val words: Words) {

    var count: Int = 0
        private set

    var results: MutableList<List<Tile>> = ArrayList()
        private set

    constructor(words: List<Word>) : this(Words(words))

    fun isGameOver(answer: Word): Boolean {
        return count >= TOTAL_ROUNDS_NUM || words.isCorrect(answer)
    }

    fun match(answer: Word) {
        require(words.contains(answer)) { "등록된 단어가 아닙니다." }
        results.add(words.check(answer))
        count++
    }
}
