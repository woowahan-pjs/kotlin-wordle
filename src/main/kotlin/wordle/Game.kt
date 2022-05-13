package wordle

class Game(private val words: Words) {

    constructor(words: List<Word>) : this(Words(words))

    var count: Int = 0
    private set

    fun isGameOver(): Boolean {
        return count == 6
    }

    fun match(answer: Word): List<Tile> {
        count++
        require(words.contains(answer)) { "등록된 단어가 아닙니다." }
        return words.check(answer)
    }
}
