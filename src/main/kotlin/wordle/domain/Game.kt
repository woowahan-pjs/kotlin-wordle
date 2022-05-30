package wordle.domain

private const val TOTAL_ROUNDS_NUM = 6

class Game(
    private val words: Words,
    val totalCount: Int = TOTAL_ROUNDS_NUM
) {

    var count: Int = 0
        private set

    var results: MutableList<List<Tile>> = ArrayList()
        private set

    constructor(
        words: List<Word>,
        totalCount: Int = TOTAL_ROUNDS_NUM
    ) : this(Words(words), totalCount)

    fun isGameOver(answer: Word): Boolean {
        return count >= totalCount || words.isCorrect(answer)
    }

    fun match(answer: Word) {
        require(words.contains(answer)) { "등록된 단어가 아닙니다." }
        results.add(words.check(answer))
        count++
    }
}
