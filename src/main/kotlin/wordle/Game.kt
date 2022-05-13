package wordle

class Game(private val words: Words) {

    constructor(words: List<Word>) : this(Words(words))

    var count: Int = 0
        private set

    var results: MutableList<List<Tile>> = ArrayList()
        private set

    fun isGameOver(answer: Word): Boolean {
        return count == 6 || words.isCorrect(answer)
    }

    fun match(answer: Word) {
        count++
        require(words.contains(answer)) { "등록된 단어가 아닙니다." }
        results.add(words.check(answer))
    }
}
