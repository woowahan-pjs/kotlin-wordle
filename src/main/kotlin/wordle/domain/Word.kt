package wordle.domain

data class Word private constructor(val word: List<Letter>) {
    init {
        require(word.size == WORD_SIZE) { "단어는 " + WORD_SIZE + "글자여야 합니다." }
    }

    fun match(other: Word): Result {
        val result: MutableList<MatchResult> = MutableList(5) { MatchResult.GRAY }

        val greenPositions: List<Int> = checkGreen(other.word)
        val yellowPositions: List<Int> = checkYellow(other.word, greenPositions.toMutableList())

        greenPositions.forEach { result[it] = MatchResult.GREEN }
        yellowPositions.forEach { result[it] = MatchResult.YELLOW }
        return Result(result.toList())
    }

    private fun checkGreen(other: List<Letter>): List<Int> =
        other.filter { this.match(it, mutableListOf()) == MatchResult.GREEN }.map { it.position }

    private fun checkYellow(other: List<Letter>, excludeGreenIndex: MutableList<Int>): List<Int> {
        val excludeYellowIndex: MutableList<Int> = excludeGreenIndex.toMutableList()
        return other.filter {
            excludeGreenIndex.contains(it.position).not() && this.match(
                it,
                excludeYellowIndex
            ) == MatchResult.YELLOW
        }
            .map { it.position }
    }

    private fun match(other: Letter, excludeIndex: MutableList<Int>): MatchResult {
        word.filterNot { excludeIndex.contains(it.position) }
            .filterNot { it.match(other) == MatchResult.GRAY }
            .forEach {
                excludeIndex.add(it.position)
                return it.match(other)
            }
        return MatchResult.GRAY
    }

    companion object {
        private const val WORD_SIZE = 5

        fun from(word: String): Word {
            require(WordPool.words.contains(word)) { "허용되지 않는 단어입니다." }
            return Word(word.mapIndexed { index, letter -> Letter(index, letter) })
        }
    }
}
