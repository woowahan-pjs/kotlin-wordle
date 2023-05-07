package wordle.domain

class Word private constructor(val word: List<Letter>) {
    init {
        require(word.size == 5) { "단어는 5글자여야 합니다." }
    }

    fun match(other: Word): List<MatchResult> {
        var result: MutableList<MatchResult> = MutableList(5) { MatchResult.GRAY }

        val greenPositions: List<Int> = checkGreen(other.word)
        val yellowPositions: List<Int> = checkYellow(other.word, greenPositions)

        greenPositions.forEach { result[it] = MatchResult.GREEN }
        yellowPositions.forEach { result[it] = MatchResult.YELLOW }
        return result.toList();
    }

    private fun checkGreen(other: List<Letter>): List<Int> =
        other.filter { this.match(it, listOf()) == MatchResult.GREEN }.map { it.position }


    private fun checkYellow(other: List<Letter>, excludeIndex: List<Int>): List<Int> =
        other.filter { excludeIndex.contains(it.position).not() && this.match(it, excludeIndex) == MatchResult.YELLOW }
            .map { it.position }

    private fun match(other: Letter, excludeIndex: List<Int>): MatchResult {
        val matchResults = word.filterNot { excludeIndex.contains(it.position) }.map { it.match(other) }
        return when {
            matchResults.contains(MatchResult.GREEN) -> MatchResult.GREEN
            matchResults.contains(MatchResult.YELLOW) -> MatchResult.YELLOW
            else -> MatchResult.GRAY
        }
    }

    companion object {
        fun from(word: String): Word {
            require(WordPool.words.contains(word)) { "허용되지 않는 단어입니다." }
            return Word(word.mapIndexed { index, letter -> Letter(index, letter) })
        }
    }
}
